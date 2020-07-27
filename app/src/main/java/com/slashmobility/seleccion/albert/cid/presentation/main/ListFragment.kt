package com.slashmobility.seleccion.albert.cid.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupListUseCaseImpl
import com.slashmobility.seleccion.albert.cid.presentation.main.state.MainViewState
import com.xpertai.test.domain.imageloader.GlideImplementation
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.Dispatchers

class ListFragment : Fragment() {

    private val imagesLoader = GlideImplementation()
    private lateinit var groupAdapter: GroupListAdapter
    private lateinit var viewModel: MainListViewModelImpl
    private val viewModelFactory = MainListViewModelFactory(GetGroupListUseCaseImpl(), Dispatchers.IO)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        groupAdapter = GroupListAdapter(imagesLoader)
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)[MainListViewModelImpl::class.java]
        setUpUI()
    }

    override fun onResume() {
        super.onResume()
        viewModel.mainViewState.observe(::getLifecycle, ::updateUI)
        viewModel.getGroups()
    }

    private fun setUpUI() {
        activity?.actionBar?.title = getString(R.string.app_name)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)

        listView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            this.adapter = groupAdapter
        }

        groupAdapter.onClickItem = {
            val bundle = bundleOf(GROUP_ID to it)
            findNavController().navigate(R.id.action_List_to_Detail, bundle)
        }
    }

    private fun updateUI(screenState: MainViewState) {
        when (screenState) {
            is MainViewState.Loading -> showLoadingDialogFragment()
            is MainViewState.ShowFullData -> showGroups(screenState.groups)
            is MainViewState.ShowFavorites -> showGroups(screenState.groups)
            is MainViewState.Error -> showErrorDialogFragment()
        }
    }

    private fun showGroups(groups: List<Group>) { groupAdapter.groupList = groups.toList() }

    private fun showLoadingDialogFragment() {}

    private fun showErrorDialogFragment() {}

}
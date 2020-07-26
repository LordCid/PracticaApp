package com.slashmobility.seleccion.albert.cid.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupsUseCase
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupsUseCaseImpl
import com.xpertai.test.domain.imageloader.GlideImplementation
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.Dispatchers

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 *
 *
 */

class ListFragment : Fragment() {

    private val imagesLoader = GlideImplementation()


    private lateinit var groupAdapter: GroupListAdapter

    private lateinit var viewmodel: MainListViewModelImpl

    private val viewModelFactory = MainListViewModelFactory(GetGroupsUseCaseImpl(), Dispatchers.IO)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        groupAdapter = GroupListAdapter(imagesLoader)
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProviders.of(requireActivity(), viewModelFactory)[MainListViewModelImpl::class.java]
        setUpUI()
    }

    private fun setUpUI() {
        listView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            this.adapter = groupAdapter
        }

        groupAdapter.onClickItem = {
            val bundle = bundleOf(GROUP_ID to it)
            findNavController().navigate(R.id.action_List_to_Detail, bundle)
        }
    }
}
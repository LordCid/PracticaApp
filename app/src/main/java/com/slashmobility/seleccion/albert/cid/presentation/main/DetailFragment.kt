package com.slashmobility.seleccion.albert.cid.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupsUseCaseImpl
import com.slashmobility.seleccion.albert.cid.presentation.main.state.DetailViewState
import com.slashmobility.seleccion.albert.cid.presentation.main.state.MainViewState
import com.xpertai.test.domain.imageloader.GlideImplementation
import kotlinx.android.synthetic.main.appbar.*
import kotlinx.coroutines.Dispatchers

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Fragment() {
    private val imagesLoader = GlideImplementation()

    private lateinit var viewModel: MainListViewModelImpl

    private val viewModelFactory = MainListViewModelFactory(GetGroupsUseCaseImpl(), Dispatchers.IO)

    private val groupId: Int by lazy { arguments?.getInt(GROUP_ID) ?:0 }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(
            requireActivity(),
            viewModelFactory
        )[MainListViewModelImpl::class.java]

        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
//        activity?.actionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onResume() {
        super.onResume()
//        viewModel.mainViewState.observe(::getLifecycle, ::updateUI)
        viewModel.getGroups()
    }

//    private fun updateUI(screenState: MainViewState) {
//        when (screenState) {
//           is MainViewState.ShowFullData -> showGroupData(screenState.groups)
//           is MainViewState.Error -> showError()
//        }
//    }
//
//    private fun showGroupData(group: Array<out Group>) {
//        val g = group.
//        TODO("Not yet implemented")
//    }



    private fun showError() {

    }
}
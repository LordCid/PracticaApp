package com.slashmobility.seleccion.albert.cid.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupListUseCaseImpl
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupUseCaseImpl
import com.slashmobility.seleccion.albert.cid.presentation.main.GROUP_ID
import com.slashmobility.seleccion.albert.cid.presentation.main.MainListViewModelFactory
import com.slashmobility.seleccion.albert.cid.presentation.main.MainListViewModelImpl
import com.xpertai.test.domain.imageloader.GlideImplementation
import kotlinx.android.synthetic.main.appbar.*
import kotlinx.coroutines.Dispatchers

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Fragment() {
    private val imagesLoader = GlideImplementation()

    private lateinit var viewModel: DetailViewModel

    private val viewModelFactory =
        DetailViewModelFactory(
            GetGroupUseCaseImpl(),
            Dispatchers.IO
        )

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
        )[DetailViewModelImpl::class.java]


//        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
//        activity?.actionBar?.setDisplayShowHomeEnabled(true)

//        activity?.toolbar?.setNavigationOnClickListener { activity?.onBackPressed() }

//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onResume() {
        super.onResume()
        activity?.actionBar?.title = getString(R.string.detail_title)
//        viewModel.mainViewState.observe(::getLifecycle, ::updateUI)
        viewModel.getGroup()
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
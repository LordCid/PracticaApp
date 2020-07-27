package com.slashmobility.seleccion.albert.cid.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupUseCaseImpl
import com.slashmobility.seleccion.albert.cid.presentation.main.BaseActivity
import com.slashmobility.seleccion.albert.cid.presentation.main.GROUP_ID
import com.xpertai.test.domain.imageloader.GlideImplementation
import kotlinx.android.synthetic.main.appbar.*
import kotlinx.coroutines.Dispatchers

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailActivity : BaseActivity() {
    private val imagesLoader = GlideImplementation()

    private lateinit var viewModel: DetailViewModel

    private val viewModelFactory =
        DetailViewModelFactory(
            GetGroupUseCaseImpl(),
            Dispatchers.IO
        )

    private val groupId: Int by lazy { intent?.extras?.getInt(GROUP_ID) ?: 0 }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_detail)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[DetailViewModelImpl::class.java]

        supportActionBar?.title = "Fútbol"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)
//        activity?.actionBar?.setDisplayShowHomeEnabled(true)

//        activity?.toolbar?.setNavigationOnClickListener { activity?.onBackPressed() }


//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onResume() {
        super.onResume()
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
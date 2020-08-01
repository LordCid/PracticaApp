package com.slashmobility.seleccion.albert.cid.presentation.main

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProviders
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.presentation.common.BaseActivity
import com.slashmobility.seleccion.albert.cid.presentation.common.ErrorDialogFragment
import com.slashmobility.seleccion.albert.cid.presentation.favorites.FavoritesActivity
import com.slashmobility.seleccion.albert.cid.presentation.main.state.MainViewState
import kotlinx.android.synthetic.main.appbar.*

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainListViewModel
    private lateinit var progressDialog: ProgressDialog
    private lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        listFragment = ListFragment(imagesLoader)
        setUpUI()
        setViewModel()
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorites -> {
                startActivity(Intent(this, FavoritesActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setViewModel(){
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[MainListViewModelImpl::class.java]
        viewModel.mainViewState.observe(::getLifecycle, ::updateUI)
        viewModel.getGroups()
    }

    private fun setUpUI() {
        progressDialog = ProgressDialog(this)
        refresh_btn.visibility = VISIBLE
        refresh_btn.setOnClickListener { viewModel.getGroups() }
        with(supportFragmentManager.beginTransaction()){
            add(R.id.fragment_list, listFragment)
            commit()
        }
    }

    private fun updateUI(screenState: MainViewState) {
        when (screenState) {
            is MainViewState.Loading -> showLoadingDialogFragment()
            is MainViewState.ShowFullData ->{
                progressDialog.dismiss()
                listFragment.showGroups(screenState.groups)
            }
            is MainViewState.Error -> {
                showErrorDialogFragment()
                listFragment.showNoGroupsLabel()
            }
        }
    }

    private fun showLoadingDialogFragment() {
        progressDialog.setMessage(getString(R.string.downloading_title_dialog))
        progressDialog.show()
    }

    private fun showErrorDialogFragment() {
        progressDialog.dismiss()
        val errorDialog = ErrorDialogFragment()
        errorDialog.show(supportFragmentManager, "error")
    }


}
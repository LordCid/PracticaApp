package com.slashmobility.seleccion.albert.cid.presentation.favorites

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.presentation.favorites.state.FavoritesViewState
import com.slashmobility.seleccion.albert.cid.presentation.common.BaseActivity
import com.slashmobility.seleccion.albert.cid.presentation.main.ListFragment

class FavoritesActivity : BaseActivity() {
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_favorites)
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        listFragment = ListFragment(imagesLoader)
        setUpUI()
        setViewModel()
    }

    private fun setViewModel(){
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[FavoritesViewModelImpl::class.java]
        viewModel.favViewState.observe(::getLifecycle, ::updateUI)
        viewModel.getFavoriteGroups()
    }


    private fun setUpUI() {
        with(supportFragmentManager.beginTransaction()){
            add(R.id.fragment_list, listFragment)
            commit()
        }
    }

    private fun updateUI(screenState: FavoritesViewState){
        when(screenState){
            is FavoritesViewState.None -> listFragment.showNoGroupsLabel()
            is FavoritesViewState.ShowGroups -> listFragment.showGroups(screenState.groups)
        }
    }
}
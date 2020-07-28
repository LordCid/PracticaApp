package com.slashmobility.seleccion.albert.cid.presentation.favorites

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetFavoritesGroupsUseCaseImpl
import com.slashmobility.seleccion.albert.cid.presentation.favorites.state.FavoritesViewState
import com.slashmobility.seleccion.albert.cid.presentation.BaseActivity
import com.slashmobility.seleccion.albert.cid.presentation.main.GroupListAdapter
import com.xpertai.test.domain.imageloader.GlideImplementation
import kotlinx.android.synthetic.main.activity_favorites.*
import kotlinx.coroutines.Dispatchers

class FavoritesActivity : BaseActivity() {

    private val imagesLoader = GlideImplementation()
    private lateinit var groupAdapter: GroupListAdapter
    private lateinit var viewModel: FavoritesViewModel
    private val viewModelFactory = FavoritesViewModelFactory(GetFavoritesGroupsUseCaseImpl(), Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_favorites)
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun updateUI(screenState: FavoritesViewState){
        when(screenState){
            is FavoritesViewState.None -> showNoFavoritesMessage()
            is FavoritesViewState.ShowGroups -> showGroups(screenState.groups)
        }
    }

    private fun showGroups(groups: List<Group>) {
        TODO("Not yet implemented")
    }

    private fun showNoFavoritesMessage() {
        favoriteListView.visibility = GONE
        no_favorites_tv.visibility = VISIBLE
    }
}
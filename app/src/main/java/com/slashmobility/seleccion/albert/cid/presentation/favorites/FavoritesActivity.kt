package com.slashmobility.seleccion.albert.cid.presentation.favorites

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.domain.GROUP_ID
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.presentation.favorites.state.FavoritesViewState
import com.slashmobility.seleccion.albert.cid.presentation.common.BaseActivity
import com.slashmobility.seleccion.albert.cid.presentation.detail.DetailActivity
import com.slashmobility.seleccion.albert.cid.presentation.main.GroupListAdapter
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : BaseActivity() {

    private lateinit var groupAdapter: GroupListAdapter
    private lateinit var viewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_favorites)
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        groupAdapter = GroupListAdapter(imagesLoader, dateFormat)
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[FavoritesViewModelImpl::class.java]
        setUpUI()
    }

    override fun onResume() {
        super.onResume()
        viewModel.favViewState.observe(::getLifecycle, ::updateUI)
        viewModel.getFavoriteGroups()
    }

    private fun setUpUI() {
        favoriteListView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            this.adapter = groupAdapter
        }

        groupAdapter.onClickItem = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(GROUP_ID, it)
            startActivity(intent)
        }
    }

    private fun updateUI(screenState: FavoritesViewState){
        when(screenState){
            is FavoritesViewState.None -> showNoFavoritesMessage()
            is FavoritesViewState.ShowGroups -> showGroups(screenState.groups)
        }
    }

    private fun showGroups(groups: List<Group>) {
        favoriteListView.visibility = VISIBLE
        no_favorites_tv.visibility = GONE
        groupAdapter.groupList = groups
    }

    private fun showNoFavoritesMessage() {
        favoriteListView.visibility = GONE
        no_favorites_tv.visibility = VISIBLE
    }
}
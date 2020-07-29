package com.slashmobility.seleccion.albert.cid.presentation.main

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupListUseCaseImpl
import com.slashmobility.seleccion.albert.cid.presentation.common.BaseActivity
import com.slashmobility.seleccion.albert.cid.presentation.common.ErrorDialogFragment
import com.slashmobility.seleccion.albert.cid.presentation.detail.DetailActivity
import com.slashmobility.seleccion.albert.cid.presentation.favorites.FavoritesActivity
import com.slashmobility.seleccion.albert.cid.presentation.main.state.MainViewState

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar.*
import kotlinx.android.synthetic.main.fragment_list.listView

const val GROUP_ID = "GROUP_ID"
class MainActivity : BaseActivity() {

    private lateinit var groupAdapter: GroupListAdapter
    private lateinit var viewModel: MainListViewModel

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        groupAdapter = GroupListAdapter(imagesLoader)
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

        listView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            this.adapter = groupAdapter
        }

        groupAdapter.onClickItem = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(GROUP_ID, it)
            startActivity(intent)
//            val bundle = bundleOf(GROUP_ID to it)
//            findNavController(this,R.id.action_List_to_Detail).navigate(R.id.action_List_to_Detail, bundle)
        }
    }

    private fun updateUI(screenState: MainViewState) {
        when (screenState) {
            is MainViewState.Loading -> showLoadingDialogFragment()
            is MainViewState.ShowFullData -> showGroups(screenState.groups)
            is MainViewState.Error -> {
                showErrorDialogFragment()
                showNoGroupsLabel()
            }
        }
    }

    private fun showLoadingDialogFragment() {
        progressDialog.setMessage(getString(R.string.downloading_title_dialog))
        progressDialog.show()
    }

    private fun showGroups(groups: List<Group>) {
        progressDialog.dismiss()
        groupAdapter.groupList = groups
    }

    private fun showErrorDialogFragment() {
        progressDialog.dismiss()
        val errorDialog = ErrorDialogFragment()
        errorDialog.show(supportFragmentManager, "error")
    }

    private fun showNoGroupsLabel() {
        listView.visibility = GONE
        no_groups_tv.text = getString(R.string.no_groups_label)
        no_groups_tv.visibility = VISIBLE
    }

}
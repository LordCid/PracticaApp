package com.slashmobility.seleccion.albert.cid.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupsUseCaseImpl
import com.slashmobility.seleccion.albert.cid.presentation.main.state.MainViewState
import com.xpertai.test.domain.imageloader.GlideImplementation
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.Dispatchers

const val GROUP_ID = "GROUP_ID"
class MainActivity : AppCompatActivity() {

    private val imagesLoader = GlideImplementation()

    private lateinit var groupAdapter: GroupListAdapter

    private lateinit var viewModel: MainListViewModelImpl

    private val viewModelFactory = MainListViewModelFactory(GetGroupsUseCaseImpl(), Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        groupAdapter = GroupListAdapter(imagesLoader)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainListViewModelImpl::class.java]
        setUpUI()
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onResume() {
        super.onResume()
        viewModel.mainViewState.observe(::getLifecycle, ::updateUI)
        viewModel.getGroups()
    }

    private fun setUpUI() {
        listView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            this.adapter = groupAdapter
        }

        groupAdapter.onClickItem = {
            val bundle = bundleOf(GROUP_ID to it)
//            findNavController().navigate(R.id.action_List_to_Detail, bundle)
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
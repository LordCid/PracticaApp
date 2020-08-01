package com.slashmobility.seleccion.albert.cid.presentation.main

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.domain.GROUP_ID
import com.slashmobility.seleccion.albert.cid.domain.common.imageloader.ImagesLoader
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.slashmobility.seleccion.albert.cid.presentation.detail.DetailActivity
import com.slashmobility.seleccion.albert.cid.presentation.imagedetail.ARG_URL
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_favorites.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.item_image.*
import javax.inject.Inject

class ListFragment(private val imagesLoader: ImagesLoader): Fragment() {

    lateinit var groupAdapter: GroupListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AndroidInjection.inject(activity)
        val dateFormat = DateFormat.getDateFormat(activity)
        groupAdapter = GroupListAdapter(imagesLoader, dateFormat)
        setUpUI()
    }

    private fun setUpUI() {
        listView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            this.adapter = groupAdapter
        }

        groupAdapter.onClickItem = {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(GROUP_ID, it)
            startActivity(intent)
        }
    }

    fun showGroups(groups: List<Group>) {
        listView.visibility = View.VISIBLE
        no_groups_tv.visibility = View.GONE
        groupAdapter.groupList = groups
    }

    fun showNoGroupsLabel() {
        listView.visibility = View.GONE
        no_groups_tv.visibility = View.VISIBLE
    }
}
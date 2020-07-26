package com.slashmobility.seleccion.albert.cid.presentation.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import kotlin.properties.Delegates

class GroupListAdapter: RecyclerView.Adapter<ListItemViewHolder>() {
    var groupList: List<Group> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

class ListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

}

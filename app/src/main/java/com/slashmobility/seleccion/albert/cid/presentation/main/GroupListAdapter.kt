package com.slashmobility.seleccion.albert.cid.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import com.xpertai.test.domain.imageloader.ImagesLoader
import kotlinx.android.synthetic.main.item_list.view.*
import kotlin.properties.Delegates

class GroupListAdapter(
    private val imagesLoader: ImagesLoader
): RecyclerView.Adapter<ListItemViewHolder>() {

    var groupList: List<Group> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            notifyDataSetChanged()
        }
    }

    var onClickItem: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_list, parent, false)
        return ListItemViewHolder(imagesLoader, view, onClickItem)
    }

    override fun getItemCount() = groupList.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(groupList[position])
    }
}

class ListItemViewHolder(
    private val imageLoader: ImagesLoader,
    itemView: View,
    private val onClick: (Int) -> Unit
): RecyclerView.ViewHolder(itemView) {

    fun bind(group: Group){
        with(itemView){
            setOnClickListener { onClick(group.id) }
            imageLoader.loadImage(group.defaultImageUrl, group_container)
            title_tv.text = group.name
            date_tv.text = group.dateLong.toString()
            description_tv.text = group.descriptionShort
        }
    }

}

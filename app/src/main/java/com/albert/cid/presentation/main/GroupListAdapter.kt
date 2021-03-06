package com.albert.cid.presentation.main

import java.text.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albert.cid.R
import com.albert.cid.domain.model.Group
import com.albert.cid.domain.common.imageloader.ImagesLoader
import kotlinx.android.synthetic.main.item_list.view.*
import kotlin.properties.Delegates

class GroupListAdapter(
    private val imagesLoader: ImagesLoader,
    private val dateFormat: DateFormat
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
        return ListItemViewHolder(imagesLoader, dateFormat, view, onClickItem)
    }

    override fun getItemCount() = groupList.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(groupList[position])
    }
}

class ListItemViewHolder(
    private val imageLoader: ImagesLoader,
    private val dateFormat: DateFormat,
    itemView: View,
    private val onClick: (Int) -> Unit
): RecyclerView.ViewHolder(itemView) {

    fun bind(group: Group){
        with(itemView){
            setOnClickListener { onClick(group.id) }
            imageLoader.loadImage(group.defaultImageUrl, group_container)
            title_tv.text = group.name
            date_tv.text = dateFormat.format(group.dateLong)
            description_short_tv.text = group.descriptionShort
        }
    }

}

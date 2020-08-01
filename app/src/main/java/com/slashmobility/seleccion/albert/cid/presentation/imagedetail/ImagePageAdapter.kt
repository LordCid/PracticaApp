package com.slashmobility.seleccion.albert.cid.presentation.imagedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.slashmobility.seleccion.albert.cid.domain.common.imageloader.ImagesLoader
import kotlin.properties.Delegates

class ImagePageAdapter(private val imagesLoader: ImagesLoader, fm: FragmentManager) :
    FragmentStatePagerAdapter(fm) {
    var urlList: List<String> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            notifyDataSetChanged()
        }
    }

    override fun getCount() = urlList.size

    override fun getItem(position: Int): Fragment {
        val fragment = ImageFragment(imagesLoader)
        fragment.arguments = Bundle().apply { putString(ARG_URL, urlList[position]) }
        return fragment
    }
}
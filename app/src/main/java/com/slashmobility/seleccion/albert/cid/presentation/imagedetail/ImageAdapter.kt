package com.slashmobility.seleccion.albert.cid.presentation.imagedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.slashmobility.seleccion.albert.cid.domain.common.imageloader.ImagesLoader
import kotlin.properties.Delegates

class ImageAdapter(private val imagesLoader: ImagesLoader, fm: FragmentManager): FragmentStatePagerAdapter(fm) {
    var urlList: List<String> by Delegates.observable(emptyList()){ _, oldValue, newValue ->
        if (oldValue != newValue) {
            notifyDataSetChanged()
        }
    }

//    override fun isViewFromObject(view: View, `object`: Any): Boolean{
//        return view == `object` as FrameLayout
//    }
//
//
    override fun getCount() = urlList.size

//
//    override fun instantiateItem(container: ViewGroup, position: Int): View {
//        val inflater: LayoutInflater = LayoutInflater.from(container.context)
//        val view: View = inflater.inflate(R.layout.item_image, container, false)
//        imagesLoader.loadImage(urlList[position], view.image)
//        return view
//    }

    override fun getItem(position: Int): Fragment {
        val fragment = ImageFragment(imagesLoader)
        fragment.arguments = Bundle().apply { putString(ARG_URL, urlList[position]) }
        return fragment
    }

//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as View)
//    }
}
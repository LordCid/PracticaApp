package com.slashmobility.seleccion.albert.cid.presentation.imagedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.slashmobility.seleccion.albert.cid.R
import com.xpertai.test.domain.imageloader.ImagesLoader
import kotlinx.android.synthetic.main.item_image.view.*
import kotlin.properties.Delegates

class ImageAdapter(private val imagesLoader: ImagesLoader): PagerAdapter() {
    var urlList: List<String> by Delegates.observable(emptyList()){ _, oldValue, newValue ->
        if (oldValue != newValue) {
            notifyDataSetChanged()
        }
    }

    override fun isViewFromObject(view: View, `object`: Any) = view == `object` as ConstraintLayout


    override fun getCount() = urlList.size

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val inflater: LayoutInflater = LayoutInflater.from(container.context)
        val view: View = inflater.inflate(R.layout.item_image, container, false)
        imagesLoader.loadImage(urlList[position], view.image)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: ConstraintLayout) {
        container.removeView(`object`)
    }
}
package com.slashmobility.seleccion.albert.cid.presentation.imagedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.slashmobility.seleccion.albert.cid.R
import com.slashmobility.seleccion.albert.cid.domain.common.imageloader.ImagesLoader
import kotlinx.android.synthetic.main.item_image.*

const val ARG_URL = "imageUrl"
class ImageFragment(private val imagesLoader: ImagesLoader): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_URL) }?.apply {
            imagesLoader.loadImage(getString(ARG_URL), image)
        }
    }
}
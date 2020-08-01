package com.slashmobility.seleccion.albert.cid.presentation.imagedetail

import androidx.lifecycle.LiveData

interface ImageGalleryViewModel {
    val viewState: LiveData<ImageGalleryState>
    fun getImages(id: Int)
}
package com.slashmobility.seleccion.albert.cid.presentation.imagedetail

import androidx.lifecycle.LiveData

interface PhotoGalleryViewModel {
    val viewState: LiveData<PhotoGalleryState>
    fun getImages(id: Int)
}
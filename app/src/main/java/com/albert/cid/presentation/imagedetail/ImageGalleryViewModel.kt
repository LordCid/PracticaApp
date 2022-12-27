package com.albert.cid.presentation.imagedetail

import androidx.lifecycle.LiveData

interface ImageGalleryViewModel {
    val viewState: LiveData<ImageGalleryState>
    fun getImages(id: Int)
}
package com.albert.cid.presentation.imagedetail

sealed class ImageGalleryState {
    class ShowImages(val images: List<String>) : ImageGalleryState()
    object Error : ImageGalleryState()
}

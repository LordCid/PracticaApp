package com.slashmobility.seleccion.albert.cid.presentation.imagedetail

sealed class PhotoGalleryState {
    class ShowImages(val images: List<String>) : PhotoGalleryState()
    object Error : PhotoGalleryState()
}

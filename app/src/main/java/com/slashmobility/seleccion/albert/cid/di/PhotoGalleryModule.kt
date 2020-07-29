package com.slashmobility.seleccion.albert.cid.di

import androidx.lifecycle.ViewModelProvider
import com.slashmobility.seleccion.albert.cid.presentation.imagedetail.PhotoGalleryViewModeFactory
import dagger.Binds
import dagger.Module

@Module
interface PhotoGalleryModule {
    @Binds
    fun bindPhotoGalleryViewModelFactory(viewModelFactory: PhotoGalleryViewModeFactory): ViewModelProvider.NewInstanceFactory

}
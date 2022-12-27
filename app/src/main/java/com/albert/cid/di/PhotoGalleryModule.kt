package com.albert.cid.di

import androidx.lifecycle.ViewModelProvider
import com.albert.cid.presentation.imagedetail.PhotoGalleryViewModeFactory
import dagger.Binds
import dagger.Module

@Module
interface PhotoGalleryModule {
    @Binds
    fun bindPhotoGalleryViewModelFactory(viewModelFactory: PhotoGalleryViewModeFactory): ViewModelProvider.NewInstanceFactory

}
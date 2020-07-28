package com.slashmobility.seleccion.albert.cid.di


import com.xpertai.test.domain.imageloader.GlideImplementation
import com.xpertai.test.domain.imageloader.ImagesLoader
import dagger.Binds
import dagger.Module

@Module
interface ImageLoaderModule {
    @Binds
    fun bindImagesLoader(imagesLoader: GlideImplementation): ImagesLoader
}
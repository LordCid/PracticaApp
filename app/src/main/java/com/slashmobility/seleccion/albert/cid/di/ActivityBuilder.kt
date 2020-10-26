package com.slashmobility.seleccion.albert.cid.di


import com.slashmobility.seleccion.albert.cid.presentation.detail.DetailActivity
import com.slashmobility.seleccion.albert.cid.presentation.favorites.FavoritesActivity
import com.slashmobility.seleccion.albert.cid.presentation.imagedetail.ImageGalleryActivity
import com.slashmobility.seleccion.albert.cid.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [
        DomainModule::class,
        DataModule::class,
        ProvidesModule::class,
        ImageLoaderModule::class
    ]
)
interface ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainListModule::class])
    fun bindMainListActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FavoritesModule::class])
    fun bindFavoritetListActivity(): FavoritesActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
    fun bindDetailActivity(): DetailActivity

    @ContributesAndroidInjector(modules = [PhotoGalleryModule::class])
    fun bindImageGalleryActivity(): ImageGalleryActivity

}
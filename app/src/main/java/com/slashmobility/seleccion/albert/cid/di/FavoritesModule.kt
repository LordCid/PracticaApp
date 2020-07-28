package com.slashmobility.seleccion.albert.cid.di

import androidx.lifecycle.ViewModelProvider
import com.slashmobility.seleccion.albert.cid.presentation.favorites.FavoritesViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface FavoritesModule {
    @Binds
    fun bindFavoritesViewModelFactory(viewModelFactory: FavoritesViewModelFactory): ViewModelProvider.NewInstanceFactory
}

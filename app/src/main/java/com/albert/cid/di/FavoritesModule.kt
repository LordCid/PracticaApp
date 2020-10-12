package com.albert.cid.di

import androidx.lifecycle.ViewModelProvider
import com.albert.cid.presentation.favorites.FavoritesViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface FavoritesModule {
    @Binds
    fun bindFavoritesViewModelFactory(viewModelFactory: FavoritesViewModelFactory): ViewModelProvider.NewInstanceFactory
}

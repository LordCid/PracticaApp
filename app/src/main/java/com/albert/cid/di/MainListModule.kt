package com.albert.cid.di

import androidx.lifecycle.ViewModelProvider
import com.albert.cid.presentation.main.MainListViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface MainListModule {
    @Binds
    fun bindMainViewModelFactory(viewModelFactory: MainListViewModelFactory): ViewModelProvider.NewInstanceFactory
}

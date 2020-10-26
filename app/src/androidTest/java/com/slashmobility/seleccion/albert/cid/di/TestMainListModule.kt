package com.slashmobility.seleccion.albert.cid.di

import androidx.lifecycle.ViewModelProvider
import com.slashmobility.seleccion.albert.cid.presentation.main.MainListViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface  TestMainListModule {
    @Binds
    fun bindMainViewModelFactory(viewModelFactory: MainListViewModelFactory): ViewModelProvider.NewInstanceFactory
}
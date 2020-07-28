package com.slashmobility.seleccion.albert.cid.di

import androidx.lifecycle.ViewModelProvider
import com.slashmobility.seleccion.albert.cid.presentation.detail.DetailViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface DetailModule {
    @Binds
    fun bindDetailViewModelFactory(viewModelFactory: DetailViewModelFactory): ViewModelProvider.NewInstanceFactory
}

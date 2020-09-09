package com.slashmobility.seleccion.albert.cid.di

import androidx.lifecycle.ViewModelProvider
import com.nhaarman.mockitokotlin2.mock
import com.slashmobility.seleccion.albert.cid.presentation.main.MainListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TestMainListModule {
    @Provides
    fun provideMockMainViewModelFactory(): ViewModelProvider.NewInstanceFactory =  mock<MainListViewModelFactory>()
}
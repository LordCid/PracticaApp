package com.slashmobility.seleccion.albert.cid.di

import androidx.lifecycle.ViewModelProvider
import com.slashmobility.seleccion.albert.cid.presentation.main.MainListViewModelFactory
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

@Module
class TestMainListModule {
    @Provides
    fun provideMockMainViewModelFactory(): ViewModelProvider.NewInstanceFactory =
        Mockito.mock(MainListViewModelFactory::class.java)
}
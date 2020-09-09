package com.slashmobility.seleccion.albert.cid.di

import androidx.lifecycle.ViewModelProvider
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides

@Module
class TestMainListModule {
    @Provides
    fun provideMockMainVierModelFactory() =  mock<ViewModelProvider.NewInstanceFactory>()
}
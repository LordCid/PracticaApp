package com.slashmobility.seleccion.albert.cid.di

import com.nhaarman.mockitokotlin2.mock
import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupListUseCase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object TestDomainModule {

    @Provides
    @JvmStatic
    fun provideGetGroupListUseCase(): GetGroupListUseCase = mock()

    @Provides
    @JvmStatic
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
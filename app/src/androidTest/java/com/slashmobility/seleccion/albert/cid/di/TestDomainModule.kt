package com.slashmobility.seleccion.albert.cid.di

import com.slashmobility.seleccion.albert.cid.domain.usecase.GetGroupListUseCase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.mockito.Mockito.mock

@Module
class TestDomainModule {
    @Provides
    fun providesgetGroupListUseCae(): GetGroupListUseCase {
        return mock(GetGroupListUseCase::class.java)
    }

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
package com.albert.cid.di

import android.app.Application
import com.albert.cid.domain.App
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object ApplicationProviderModule {
    @Provides
    @JvmStatic
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}

@Module(includes = [AndroidInjectionModule::class])
interface AppModule {

    @Binds
    @Singleton
    fun application(app: App): Application
}
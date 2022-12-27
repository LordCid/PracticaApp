package com.albert.cid.di

import com.albert.cid.data.local.RealmManager
import com.albert.cid.data.network.ApiService
import dagger.Module
import dagger.Provides

@Module
object ProvidesModule {
    @Provides
    @JvmStatic
    fun providesApiService() = ApiService.create()

    @Provides
    @JvmStatic
    fun providesRealmManager() = RealmManager()
}
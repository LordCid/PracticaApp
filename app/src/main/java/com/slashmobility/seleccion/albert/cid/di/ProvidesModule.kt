package com.slashmobility.seleccion.albert.cid.di

import com.slashmobility.seleccion.albert.cid.data.local.RealmManager
import com.slashmobility.seleccion.albert.cid.data.network.ApiService
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
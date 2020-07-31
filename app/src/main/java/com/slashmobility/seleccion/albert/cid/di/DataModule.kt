package com.slashmobility.seleccion.albert.cid.di

import com.slashmobility.seleccion.albert.cid.data.network.NetworkDataSource
import com.slashmobility.seleccion.albert.cid.data.network.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    fun bindNetworkDataSource(netWorkDataSource: NetworkDataSourceImpl): NetworkDataSource
}
package com.slashmobility.seleccion.albert.cid.di

import com.slashmobility.seleccion.albert.cid.data.network.NetWorkDataSource
import com.slashmobility.seleccion.albert.cid.data.network.NetWorkDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    fun bindNetworkDataSource(netWorkDataSource: NetWorkDataSourceImpl): NetWorkDataSource
}
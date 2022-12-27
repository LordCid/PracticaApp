package com.albert.cid.di

import com.albert.cid.data.Repository
import com.albert.cid.data.RepositoryImpl
import com.albert.cid.data.local.LocalDataSource
import com.albert.cid.data.local.LocalDataSourceImpl
import com.albert.cid.data.network.NetworkDataSource
import com.albert.cid.data.network.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    fun bindRepository(repository: RepositoryImpl): Repository

    @Binds
    fun bindLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    fun bindNetworkDataSource(netWorkDataSource: NetworkDataSourceImpl): NetworkDataSource
}
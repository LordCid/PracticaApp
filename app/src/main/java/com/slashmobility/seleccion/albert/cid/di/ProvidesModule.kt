package com.slashmobility.seleccion.albert.cid.di

import com.slashmobility.seleccion.albert.cid.data.ApiService
import com.slashmobility.seleccion.albert.cid.data.model.GroupNetworkModel
import com.slashmobility.seleccion.albert.cid.domain.mappers.DomainMapper
import com.slashmobility.seleccion.albert.cid.domain.mappers.Mapper
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import dagger.Module
import dagger.Provides

@Module
object ProvidesModule {
    @Provides
    @JvmStatic
    fun providesApiService() = ApiService.create()

    @Provides
    @JvmStatic
    fun providesNetworkToDomainModelMapper(): Mapper<GroupNetworkModel, Group> = DomainMapper()
}
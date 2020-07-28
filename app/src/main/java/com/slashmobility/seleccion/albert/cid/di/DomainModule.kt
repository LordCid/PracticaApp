package com.slashmobility.seleccion.albert.cid.di

import com.slashmobility.seleccion.albert.cid.domain.usecase.*
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {
    @Binds
    fun bindGetGroupListUseCase(usecase: GetGroupListUseCaseImpl): GetGroupListUseCase

    @Binds
    fun bindGetGroupUseCase(usecase: GetGroupUseCaseImpl): GetGroupUseCase

    @Binds
    fun bindSaveGroupUseCase(usecase: SaveGroupUseCaseImpl): SaveGroupUseCase

    @Binds
    fun bindFavoritesGroupUseCase(usecase: GetFavoritesGroupsUseCaseImpl): GetFavoritesGroupsUseCase
}
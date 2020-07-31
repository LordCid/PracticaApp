package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.slashmobility.seleccion.albert.cid.data.Repository
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import javax.inject.Inject

class GetFavoritesGroupsUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetFavoritesGroupsUseCase {
    override suspend fun invoke() = repository.getFavoritesGroupList()
}
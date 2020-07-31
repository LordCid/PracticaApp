package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.slashmobility.seleccion.albert.cid.data.Repository

import javax.inject.Inject

class GetGroupListUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetGroupListUseCase {
    override suspend fun invoke() = repository.getGroupList()
}
package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.slashmobility.seleccion.albert.cid.data.Repository
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import javax.inject.Inject

class GetGroupUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetGroupUseCase {
    override suspend fun invoke(id: Int) = repository.getGroupDetail(id)
}
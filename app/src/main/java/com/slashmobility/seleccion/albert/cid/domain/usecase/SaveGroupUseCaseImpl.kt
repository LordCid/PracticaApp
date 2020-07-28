package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.slashmobility.seleccion.albert.cid.domain.model.Group

class SaveGroupUseCaseImpl : SaveGroupUseCase {
    override suspend fun invoke(group: Group): Result<Unit> {
        TODO("Not yet implemented")
    }
}
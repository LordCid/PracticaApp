package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.slashmobility.seleccion.albert.cid.domain.model.Group
import javax.inject.Inject

class SaveGroupUseCaseImpl @Inject constructor(): SaveGroupUseCase {
    override suspend fun invoke(group: Group): Result<Unit> {
        return Result.success(Unit)
    }
}
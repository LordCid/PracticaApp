package com.slashmobility.seleccion.albert.cid.domain.usecase

import javax.inject.Inject

class ChangeGroupFavoriteStatusUseCaseImpl @Inject constructor(): ChangeGroupFavoriteStatusUseCase {
    override suspend fun invoke(id: Int): Result<Unit> {
        return Result.success(Unit)
    }
}
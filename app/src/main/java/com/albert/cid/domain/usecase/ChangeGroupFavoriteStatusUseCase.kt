package com.albert.cid.domain.usecase

interface ChangeGroupFavoriteStatusUseCase {
    suspend operator fun invoke(id: Int): Result<Unit>
}

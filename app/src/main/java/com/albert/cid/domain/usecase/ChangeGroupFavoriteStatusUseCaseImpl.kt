package com.albert.cid.domain.usecase

import com.albert.cid.data.Repository
import javax.inject.Inject

class ChangeGroupFavoriteStatusUseCaseImpl @Inject constructor(
    private val respository: Repository
): ChangeGroupFavoriteStatusUseCase {
    override suspend fun invoke(id: Int) = respository.changeGroupFavoriteStatus(id)
}
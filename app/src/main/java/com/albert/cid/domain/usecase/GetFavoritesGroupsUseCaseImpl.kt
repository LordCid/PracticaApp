package com.albert.cid.domain.usecase

import com.albert.cid.data.Repository
import javax.inject.Inject

class GetFavoritesGroupsUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetFavoritesGroupsUseCase {
    override suspend fun invoke() = repository.getFavoritesGroupList()
}
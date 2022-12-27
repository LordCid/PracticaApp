package com.albert.cid.domain.usecase

import com.albert.cid.data.Repository
import javax.inject.Inject

class GetGroupUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetGroupUseCase {
    override suspend fun invoke(id: Int) = repository.getGroupDetail(id)
}
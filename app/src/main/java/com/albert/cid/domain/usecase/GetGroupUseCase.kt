package com.albert.cid.domain.usecase

import com.albert.cid.domain.model.Group

interface GetGroupUseCase {
    suspend operator fun invoke(id: Int): Result<Group>
}
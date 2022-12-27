package com.albert.cid.domain.usecase

import com.albert.cid.domain.model.Group

interface GetGroupListUseCase {
    suspend operator fun invoke(): Result<List<Group>>
}
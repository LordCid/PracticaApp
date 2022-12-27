package com.albert.cid.domain.usecase

import com.albert.cid.domain.model.Group

interface GetFavoritesGroupsUseCase {
    suspend operator fun invoke(): Result<List<Group>>
}

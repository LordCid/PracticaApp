package com.albert.cid.domain.usecase

interface GetGroupImagesUseCase {
    suspend operator fun invoke(id: Int): Result<List<String>>
}
package com.slashmobility.seleccion.albert.cid.domain.usecase

interface GetGroupImagesUseCase {
    suspend operator fun invoke(): Result<List<String>>
}
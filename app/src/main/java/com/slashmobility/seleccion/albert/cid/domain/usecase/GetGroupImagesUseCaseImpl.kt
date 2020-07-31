package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.slashmobility.seleccion.albert.cid.data.Repository
import javax.inject.Inject

class GetGroupImagesUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetGroupImagesUseCase {
    override suspend fun invoke(id: Int) = repository.getGroupImages(id)
}
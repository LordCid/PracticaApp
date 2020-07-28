package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.slashmobility.seleccion.albert.cid.domain.model.Group

class GetGroupUseCaseImpl : GetGroupUseCase {
    override suspend fun invoke(id: Int): Result<Group> {
        return Result.success(group)
    }

    private val group = Group(
        id = 1,
        name = "Montain",
        description = "description",
        descriptionShort = "descriptionShort",
        defaultImageUrl = "https://homepages.cae.wisc.edu/~ece533/images/mountain.png",
        dateLong = 1677,
        isFavorite = true
    )
}
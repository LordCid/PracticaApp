package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.slashmobility.seleccion.albert.cid.domain.model.Group
import javax.inject.Inject

class GetFavoritesGroupsUseCaseImpl @Inject constructor(): GetFavoritesGroupsUseCase {
    override suspend fun invoke(): Result<List<Group>> {
       return Result.success(listOf(fakeGroup1, fakeGroup2))
//        return Result.success(emptyList())
    }

    private val fakeGroup1 = Group(
        id = 1,
        name = "Mountain",
        description = "description",
        descriptionShort = "descriptionShort",
        defaultImageUrl = "https://homepages.cae.wisc.edu/~ece533/images/mountain.png",
        dateLong = 1677,
        isFavorite = true
    )

    private val fakeGroup2 = Group(
        id = 2,
        name = "Cat",
        description = "description",
        descriptionShort = "descriptionShort",
        defaultImageUrl = "https://homepages.cae.wisc.edu/~ece533/images/cat.png",
        dateLong = 1677,
        isFavorite = true
    )
}
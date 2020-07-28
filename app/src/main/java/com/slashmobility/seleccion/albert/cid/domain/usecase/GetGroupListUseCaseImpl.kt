package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.slashmobility.seleccion.albert.cid.domain.model.Group

import javax.inject.Inject

class GetGroupListUseCaseImpl @Inject constructor() : GetGroupListUseCase {

    override suspend fun invoke(): Result<List<Group>> {
//        return Result.failure(Exception())
        return Result.success(
            listOf(
                fakeGroup1,
                fakeGroup2,
                fakeGroup3,
                fakeGroup2,
                fakeGroup2,
                fakeGroup3,
                fakeGroup4
            )
        )
    }

    private val fakeGroup1 = Group(
        id = 1,
        name = "Mountain",
        description = "description",
        descriptionShort = "descriptionShort",
        defaultImageUrl = "https://homepages.cae.wisc.edu/~ece533/images/mountain.png",
        dateLong = 1677,
        isFavorite = false
    )

    private val fakeGroup2 = Group(
        id = 2,
        name = "Cat",
        description = "description",
        descriptionShort = "descriptionShort",
        defaultImageUrl = "https://homepages.cae.wisc.edu/~ece533/images/cat.png",
        dateLong = 1677,
        isFavorite = false
    )
    private val fakeGroup3 = Group(
        id = 3,
        name = "Fruits",
        description = "description",
        descriptionShort = "descriptionShort",
        defaultImageUrl = "https://homepages.cae.wisc.edu/~ece533/images/fruits.png",
        dateLong = 1677,
        isFavorite = false
    )

    private val fakeGroup4 = Group(
        id = 4,
        name = "Frymire",
        description = "description",
        descriptionShort = "descriptionShort",
        defaultImageUrl = "https://homepages.cae.wisc.edu/~ece533/images/frymire.png",
        dateLong = 1677,
        isFavorite = false
    )
}
package com.slashmobility.seleccion.albert.cid.domain.usecase

class GetGroupImagesUseCaseImpl : GetGroupImagesUseCase {
    override suspend fun invoke(): Result<List<String>> {
        return Result.success(
            listOf(
                "https://homepages.cae.wisc.edu/~ece533/images/mountain.png",
                "https://homepages.cae.wisc.edu/~ece533/images/boat.png",
                "https://homepages.cae.wisc.edu/~ece533/images/cat.png",
                "https://homepages.cae.wisc.edu/~ece533/images/fruits.png",
                "https://homepages.cae.wisc.edu/~ece533/images/frymire.png"
            )
        )
    }
}
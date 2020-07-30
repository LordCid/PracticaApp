package com.slashmobility.seleccion.albert.cid.domain.usecase

import com.slashmobility.seleccion.albert.cid.data.network.NetWorkDataSource
import javax.inject.Inject

class GetGroupImagesUseCaseImpl @Inject constructor(
    private val netWorkDataSource: NetWorkDataSource
) : GetGroupImagesUseCase {
    override suspend fun invoke(id: Int): Result<List<String>> {
        return netWorkDataSource.getImagesList(id)
//        return Result.success(
//            listOf(
//                "https://homepages.cae.wisc.edu/~ece533/images/mountain.png",
//                "https://homepages.cae.wisc.edu/~ece533/images/boat.png",
//                "https://homepages.cae.wisc.edu/~ece533/images/cat.png",
//                "https://homepages.cae.wisc.edu/~ece533/images/fruits.png",
//                "https://homepages.cae.wisc.edu/~ece533/images/frymire.png"
//            )
//        )
    }
}
package com.slashmobility.seleccion.albert.cid.data.network

import com.slashmobility.seleccion.albert.cid.data.network.model.GroupNetworkModel
import com.slashmobility.seleccion.albert.cid.data.network.model.toGroup
import com.slashmobility.seleccion.albert.cid.domain.mappers.Mapper
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import retrofit2.awaitResponse
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : NetworkDataSource {

    override suspend fun getGroupList(): Result<List<Group>> {
        return runCatching { apiService.getGroups().awaitResponse() }.fold(
            onSuccess = {
                val resultList = it.body()?.let { response ->
                    response.map { netModel -> netModel.toGroup() }
                }.orEmpty()
                Result.success(resultList)
            },
            onFailure = { Result.failure(it) }
        )
    }

    override suspend fun getImagesList(id: Int): Result<List<String>> {
        return runCatching { apiService.getImages(id).awaitResponse() }.fold(
            onSuccess = { Result.success(it.body().orEmpty()) },
            onFailure = { Result.failure(it) }
        )
    }
}


package com.slashmobility.seleccion.albert.cid.data

import com.slashmobility.seleccion.albert.cid.data.local.LocalDataSource
import com.slashmobility.seleccion.albert.cid.data.network.NetworkDataSource
import com.slashmobility.seleccion.albert.cid.domain.model.Group

class RepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun getGroupList(): Result<List<Group>> {
        val results = networkDataSource.getGroupList()
        return if(results.isFailure) {
            localDataSource.getGroupList(false)
        } else {
            results.onSuccess { localDataSource.storeGroupList(it) }
        }

    }

    override suspend fun getFavoritesGroupList() = localDataSource.getGroupList(true)


    override suspend fun getGroupDetail(): Result<Group> {
        TODO("Not yet implemented")
    }

    override suspend fun getGroupImages(): Result<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveGroupNewFavoriteStatus(id: Int): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun storeGroupList(data: List<Group>) {
        TODO("Not yet implemented")
    }
}
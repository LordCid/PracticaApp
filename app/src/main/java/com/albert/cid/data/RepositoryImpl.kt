package com.albert.cid.data

import com.albert.cid.data.local.LocalDataSource
import com.albert.cid.data.network.NetworkDataSource
import com.albert.cid.domain.model.Group
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
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


    override suspend fun getGroupDetail(id: Int) = localDataSource.getGroup(id)


    override suspend fun getGroupImages(id: Int) = networkDataSource.getImagesList(id)

    override suspend fun changeGroupFavoriteStatus(id: Int) = localDataSource.changeFavoriteStatus(id)


}
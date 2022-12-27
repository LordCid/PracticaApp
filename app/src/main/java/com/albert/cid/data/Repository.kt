package com.albert.cid.data

import com.albert.cid.domain.model.Group

interface Repository {
    suspend fun getGroupList(): Result<List<Group>>
    suspend fun getFavoritesGroupList(): Result<List<Group>>
    suspend fun getGroupDetail(id: Int): Result<Group>
    suspend fun getGroupImages(id: Int): Result<List<String>>
    suspend fun changeGroupFavoriteStatus(id: Int): Result<Unit>
}
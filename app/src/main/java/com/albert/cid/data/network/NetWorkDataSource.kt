package com.albert.cid.data.network

import com.albert.cid.domain.model.Group

interface NetworkDataSource {
    suspend fun getGroupList(): Result<List<Group>>
    suspend fun getImagesList(id: Int): Result<List<String>>
}
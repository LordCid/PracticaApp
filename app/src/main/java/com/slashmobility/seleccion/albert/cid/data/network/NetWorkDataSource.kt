package com.slashmobility.seleccion.albert.cid.data.network

import com.slashmobility.seleccion.albert.cid.domain.model.Group

interface NetworkDataSource {
    suspend fun getGroupList(): Result<List<Group>>
    suspend fun getImagesList(id: Int): Result<List<String>>
}
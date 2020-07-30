package com.slashmobility.seleccion.albert.cid.data

import com.slashmobility.seleccion.albert.cid.domain.model.Group

interface Repository {
    suspend fun getGroupList(): Result<List<Group>>
    suspend fun getFavoritesGroupList(): Result<List<Group>>
    suspend fun getGroupDetail(): Result<Group>
    suspend fun getGroupImages(): Result<List<String>>
}
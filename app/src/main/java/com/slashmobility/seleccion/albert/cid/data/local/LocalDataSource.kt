package com.slashmobility.seleccion.albert.cid.data.local

import com.slashmobility.seleccion.albert.cid.domain.model.Group


interface LocalDataSource {
    suspend fun getGroupList(favorite: Boolean): Result<List<Group>>
    suspend fun getGroup(id: Int): Result<Group>
    suspend fun changeFavoriteStatus(id: Int): Result<Unit>
    suspend fun storeGroupList(groupList: List<Group>)
}
package com.slashmobility.seleccion.albert.cid.data.local

import com.slashmobility.seleccion.albert.cid.data.local.model.GroupRealmModel
import com.slashmobility.seleccion.albert.cid.data.local.model.toGroupRealmObject
import com.slashmobility.seleccion.albert.cid.data.local.model.toGroup
import com.slashmobility.seleccion.albert.cid.domain.App
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val realmManager: RealmManager
) : LocalDataSource {

    private lateinit var realm: Realm

    override suspend fun storeGroupList(groupList: List<Group>) {
        realm = realmManager.getRealmInstance()
        val realmList = groupList.map { it.toGroupRealmObject() }.toCollection(RealmList())
        realm.executeTransaction {
            it.deleteAll()
            it.copyToRealm(realmList)
        }
        realm.close()
    }

    override suspend fun getGroupList(favorite: Boolean): Result<List<Group>> {
        realm = realmManager.getRealmInstance()
        return runCatching {
            if(favorite){
                realm.where(GroupRealmModel::class.java).equalTo("isFavorite", true).findAll()
                    .map{ it.toGroup() }
            } else {
                realm.where(GroupRealmModel::class.java).findAll().map {
                    it.toGroup()
                }
            }
        }
    }

    override suspend fun getGroup(id: Int): Result<Group> {
        realm = realmManager.getRealmInstance()
        return runCatching {
            realm.where(GroupRealmModel::class.java).findAll().map {
                it.toGroup()
            }.find { it.id == id } ?: Group()
        }
    }

    override suspend fun changeFavoriteStatus(id: Int): Result<Unit> {
        realm = realmManager.getRealmInstance()
        return runCatching {
            realm.executeTransaction {
                val item = it.where(GroupRealmModel::class.java).equalTo("id", id).findFirst()
                item?.isFavorite?.let { value ->
                    item.isFavorite = !value
                    it.copyToRealmOrUpdate(item)
                }
            }
            realm.close()
        }
    }
}
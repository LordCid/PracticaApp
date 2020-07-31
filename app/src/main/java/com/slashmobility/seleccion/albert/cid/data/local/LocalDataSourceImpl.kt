package com.slashmobility.seleccion.albert.cid.data.local

import com.slashmobility.seleccion.albert.cid.data.local.model.GroupRealmModel
import com.slashmobility.seleccion.albert.cid.domain.App
import com.slashmobility.seleccion.albert.cid.domain.model.Group
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import javax.inject.Inject

class LocalDataSourceImpl (
    private val app: App
) : LocalDataSource {

    lateinit var realm: Realm

    private fun getRealmInstance(): Realm {
        Realm.init(app)
        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)
        return Realm.getDefaultInstance()
    }

    override suspend fun storeGroupList(groupList: List<Group>) {
        realm = getRealmInstance()
        val realmList = groupList.map { mapToDataBase(it) }.toCollection(RealmList())
        realm.executeTransaction {
            it.deleteAll()
            it.copyToRealm(realmList)
        }
        realm.close()
    }

    override suspend fun storeGroup(group: Group) {
        realm = getRealmInstance()
        realm.executeTransaction {
            val item = it.where(GroupRealmModel::class.java)
                .equalTo("id", group.id).findFirst()
            it.copyToRealmOrUpdate(item)
        }
        realm.close()
    }

    override suspend fun getGroupList(favorite: Boolean): Result<List<Group>> {
        realm = getRealmInstance()
        return runCatching {
            realm.where(GroupRealmModel::class.java).findAll().map{
                 mapToDomanin(it)
            }
        }
    }

    override suspend fun getGroup(id: Int): Result<Group> {
        realm = getRealmInstance()
        return runCatching {
            realm.where(GroupRealmModel::class.java).findAll().map{
                mapToDomanin(it)
            }.find { it.id == id } ?: Group()
        }
    }



    private fun mapToDataBase(model: Group) = GroupRealmModel(
        id = model.id,
        name = model.name,
        description = model.description,
        descriptionShort = model.descriptionShort,
        defaultImageUrl = model.defaultImageUrl,
        dateLong = model.dateLong,
        isFavorite = model.isFavorite

    )

    private fun mapToDomanin(model: GroupRealmModel) = Group(
        id = model.id,
        name = model.name,
        description = model.description,
        descriptionShort = model.descriptionShort,
        defaultImageUrl = model.defaultImageUrl,
        dateLong = model.dateLong,
        isFavorite = model.isFavorite

    )
}
package com.slashmobility.seleccion.albert.cid.data.local

import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class RealmManager @Inject constructor() {

    fun getRealmInstance(): Realm {
        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)
        return Realm.getDefaultInstance()
    }
}
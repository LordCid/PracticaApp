package com.slashmobility.seleccion.albert.cid.data.local.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class GroupRealmModel(
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var description: String = "",
    var descriptionShort: String = "",
    var defaultImageUrl: String = "",
    var dateLong: Long = 0,
    var isFavorite: Boolean = false
) : RealmModel
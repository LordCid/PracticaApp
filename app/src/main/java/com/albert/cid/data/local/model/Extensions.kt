package com.albert.cid.data.local.model

import com.albert.cid.domain.model.Group

fun Group.toGroupRealmObject() = GroupRealmModel(
    id = this.id,
    name = this.name,
    description = this.description,
    descriptionShort = this.descriptionShort,
    defaultImageUrl = this.defaultImageUrl,
    dateLong = this.dateLong,
    isFavorite = this.isFavorite
)

fun GroupRealmModel.toGroup() = Group(
    id = this.id,
    name = this.name,
    description = this.description,
    descriptionShort = this.descriptionShort,
    defaultImageUrl = this.defaultImageUrl,
    dateLong = this.dateLong,
    isFavorite = this.isFavorite
)
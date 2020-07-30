package com.slashmobility.seleccion.albert.cid

import com.slashmobility.seleccion.albert.cid.data.model.GroupNetworkModel
import com.slashmobility.seleccion.albert.cid.domain.model.Group


val concreteGroupNetworkModel = GroupNetworkModel(
    id = 1,
    name = "Name",
    description = "description",
    descriptionShort = "descriptionShort",
    defaultImageUrl = "url",
    date = 1677
)

val concreteOtherGroupNetworkModel = GroupNetworkModel(
    id = 2,
    name = "OtherName",
    description = "other description",
    descriptionShort = "other descriptionShort",
    defaultImageUrl = "url",
    date = 1677
)


val concreteGroup = Group(
    id = 1,
    name = "Name",
    description = "description",
    descriptionShort = "descriptionShort",
    defaultImageUrl = "url",
    dateLong = 1677,
    isFavorite = false
)

val concreteOtherGroup = Group(
    id = 2,
    name = "OtherName",
    description = "other description",
    descriptionShort = "other descriptionShort",
    defaultImageUrl = "url",
    dateLong = 1677,
    isFavorite = false
)
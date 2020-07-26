package com.slashmobility.seleccion.albert.cid

import com.slashmobility.seleccion.albert.cid.domain.model.Group

object GlobalTestDoubles {
}

val someGroup = Group(
    id = 1,
    name = "Name",
    description = "description",
    descriptionShort = "descriptionShort",
    defaultImageUrl = "url",
    dateLong = 1677
)

val someOtherGroup = Group(
    id = 2,
    name = "OtherName",
    description = "other description",
    descriptionShort = "other descriptionShort",
    defaultImageUrl = "url",
    dateLong = 1677
)
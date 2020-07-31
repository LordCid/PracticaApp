package com.slashmobility.seleccion.albert.cid.data.network.model

import com.slashmobility.seleccion.albert.cid.domain.model.Group

fun GroupNetworkModel.toGroup() = Group(
    id = this.id ?:0,
    name = this.name ?:"",
    description = this.description ?:"",
    descriptionShort = this.descriptionShort ?:"",
    defaultImageUrl = this.defaultImageUrl ?:"",
    dateLong =  this.date ?:0,
    isFavorite = false
)
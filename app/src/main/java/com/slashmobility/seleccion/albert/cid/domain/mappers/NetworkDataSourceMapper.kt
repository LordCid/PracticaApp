package com.slashmobility.seleccion.albert.cid.domain.mappers


import com.slashmobility.seleccion.albert.cid.data.network.model.GroupNetworkModel
import com.slashmobility.seleccion.albert.cid.domain.model.Group

class NetworkDataSourceMapper: Mapper<GroupNetworkModel, Group> {
    override fun map(model: GroupNetworkModel): Group {
        return Group(
            id = model.id ?:0,
            name = model.name ?:"",
            description = model.description ?:"",
            descriptionShort = model.descriptionShort ?:"",
            defaultImageUrl = model.defaultImageUrl ?:"",
            dateLong =  model.date ?:0,
            isFavorite = false
        )
    }
}
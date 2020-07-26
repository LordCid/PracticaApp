package com.slashmobility.seleccion.albert.cid.domain.model

data class FanGroup (
    val id: Int,
    val name: String,
    val description: String,
    val descriptionShort: String,
    val defaultImageUrl: String,
    val dateLong: Long
)
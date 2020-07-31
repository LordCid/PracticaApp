package com.slashmobility.seleccion.albert.cid.domain.model

data class Group (
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val descriptionShort: String = "",
    val defaultImageUrl: String = "",
    val dateLong: Long = 0,
    val isFavorite: Boolean = false
)
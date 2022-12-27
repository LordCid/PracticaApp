package com.albert.cid.presentation.model

data class GroupViewDetail (
    val name: String,
    val description: String,
    val defaultImageUrl: String,
    val dateLong: Long,
    val isFavorite: Boolean
)
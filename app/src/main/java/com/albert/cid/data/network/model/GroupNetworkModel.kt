package com.albert.cid.data.network.model

import com.google.gson.annotations.SerializedName

data class GroupNetworkModel(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("descriptionShort")
    val descriptionShort: String? = null,
    @SerializedName("defaultImageUrl")
    val defaultImageUrl: String? = null,
    @SerializedName("date")
    val date: Long? = null
)
package com.tripmate.android.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotDetailResponse(
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: SpotDetail,
)

@Serializable
data class SpotDetail(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("category")
    val category: String,
    @SerialName("description")
    val description: String? = null,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("location")
    val location: String,
    @SerialName("latitude")
    val latitude: Float,
    @SerialName("longitude")
    val longitude: Float,
)

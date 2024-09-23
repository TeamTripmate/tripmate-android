package com.tripmate.android.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationBasedSpotSearchResponse(
    @SerialName("data")
    var data: Data,
)

@Serializable
data class Data(
    @SerialName("spots")
    var spots: List<Spot>,
)

@Serializable
data class Spot(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String? = "",
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String,
    @SerialName("distance")
    val distance: String,
)

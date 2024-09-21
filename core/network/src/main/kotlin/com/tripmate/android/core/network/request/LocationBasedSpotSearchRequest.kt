package com.tripmate.android.core.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationBasedSpotSearchRequest(
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String,
    @SerialName("range")
    val range: String,
    @SerialName("category")
    val category: String,
)

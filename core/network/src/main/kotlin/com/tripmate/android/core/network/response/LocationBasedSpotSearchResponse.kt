package com.tripmate.android.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationBasedSpotSearchResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: Data,
    @SerialName("error")
    val error: String?,
)

@Serializable
data class Data(
    @SerialName("spots")
    val spots: List<Spot>,
)

@Serializable
data class Spot(
    @SerialName("spotId")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("spotType")
    val spotType: String,
    @SerialName("category")
    val category: Category,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerialName("location")
    val location: Location,
    @SerialName("distance")
    val distance: String,
    @SerialName("companionYn")
    val companionYn: Boolean,
)

@Serializable
data class Category(
    @SerialName("largeCategory")
    val largeCategory: String,
    @SerialName("mediumCategory")
    val mediumCategory: String,
    @SerialName("smallCategory")
    val smallCategory: String,
)

@Serializable
data class Location(
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String,
    @SerialName("address")
    val address: Address,
)

@Serializable
data class Address(
    @SerialName("address1")
    val address1: String,
    @SerialName("address2")
    val address2: String,
)

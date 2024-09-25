package com.tripmate.android.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TripDetailInfoResponse(
    @SerialName("data")
    var data: TripDetailInfo,
)

@Serializable
data class TripDetailInfo(
    @SerialName("spotId")
    val spotId: Long,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("spotType")
    val spotType: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("location")
    val location: TripDetailLocation,
    @SerialName("recommendedStyles")
    val recommendedStyles: List<RecommendedStyle>,
    @SerialName("companionRecruits")
    val companionRecruits: List<CompanionRecruit>
)

@Serializable
data class TripDetailLocation(
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String,
    @SerialName("address")
    val address: TripDetailAddress
)

@Serializable
data class TripDetailAddress(
    @SerialName("address1")
    val address1: String,
)

@Serializable
data class RecommendedStyle(
    @SerialName("characterName")
    val characterName: String,
    @SerialName("characterType")
    val characterType: String
)

@Serializable
data class CompanionRecruit(
    @SerialName("companionId")
    val companionId: Int,
    @SerialName("hostInfo")
    val hostInfo: TripDetailHostInfo,
    @SerialName("title")
    val title: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("ageRange")
    val ageRange: String
)

@Serializable
data class TripDetailHostInfo(
    @SerialName("profileImage")
    val profileImage: String,
    @SerialName("kakaoNickname")
    val kakaoNickname: String,
    @SerialName("characterName")
    val characterName: String,
    @SerialName("selectedKeyword")
    val selectedKeyword: List<String>,
    @SerialName("matchingRatio")
    val matchingRatio: Int
)

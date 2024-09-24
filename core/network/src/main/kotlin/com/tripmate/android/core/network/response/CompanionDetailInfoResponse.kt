package com.tripmate.android.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanionDetailInfoResponse(
    @SerialName("title")
    val title: String,
    @SerialName("spotId")
    val spotId: String,
    @SerialName("date")
    val date: String,
    @SerialName("accompanyYn")
    val accompanyYn: Boolean,
    @SerialName("chatLink")
    val chatLink: String,
    @SerialName("description")
    val description: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("ageRange")
    val ageRange: String,
    @SerialName("matchingRatio")
    val matchingRatio: String,
    @SerialName("hostInfo")
    val hostInfo: HostInfo,
    @SerialName("reviewInfos")
    val reviewInfos: List<ReviewInfo>,
    @SerialName("reviewRanks")
    val reviewRanks: List<String>,
)

@Serializable
data class HostInfo(
    @SerialName("profileImage")
    val profileImage: String,
    @SerialName("kakaoNickname")
    val kakaoNickname: String,
    @SerialName("characterName")
    val characterName: String,
    @SerialName("styleType")
    val styleType: List<String>,
)

@Serializable
data class ReviewInfo(
    @SerialName("userInfo")
    val userInfo: UserInfo,
    @SerialName("reviewDate")
    val reviewDate: String,
    @SerialName("likeList")
    val likeList: List<String>,
    @SerialName("badList")
    val badList: List<String>,
)

@Serializable
data class UserInfo(
    @SerialName("profileImage")
    val profileImage: String,
    @SerialName("kakaoNickname")
    val kakaoNickname: String,
    @SerialName("characterName")
    val characterName: String,
)

package com.tripmate.android.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    @SerialName("data")
    val data: MyPageUserInfo,
)

@Serializable
data class MyPageUserInfo(
    @SerialName("selectedKeyword")
    val selectedKeyword: List<String>,
    @SerialName("characterId")
    val characterId: Long,
    @SerialName("tripStyle")
    val tripStyle: String,
)

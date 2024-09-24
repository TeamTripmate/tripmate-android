package com.tripmate.android.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: UserInfo,
)

@Serializable
data class UserInfo(
    @SerialName("selectedKeyword")
    val selectedKeyword: String,
    @SerialName("characterId")
    val characterId: Long,
    @SerialName("tripStyle")
    val tripStyle: String,
)

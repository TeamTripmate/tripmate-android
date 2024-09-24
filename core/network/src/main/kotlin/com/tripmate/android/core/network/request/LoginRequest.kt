package com.tripmate.android.core.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("id")
    val id: Long,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("thumbnailImageUrl")
    val thumbnailImageUrl: String,
    @SerialName("profileImageUrl")
    val profileImageUrl: String,
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
)

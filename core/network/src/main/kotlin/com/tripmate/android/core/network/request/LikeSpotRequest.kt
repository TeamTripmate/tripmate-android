package com.tripmate.android.core.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LikeSpotRequest(
    @SerialName("spotId")
    val spotId: Long,
    @SerialName("token")
    val token: String,
)

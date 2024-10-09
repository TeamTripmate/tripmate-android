package com.tripmate.android.core.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MateSelectRequest(
    @SerialName("companionId")
    val companionId: Int,
    @SerialName("userId")
    val userId: Long,

)

package com.tripmate.android.core.network.request

import kotlinx.serialization.SerialName

class SelectCompanionRequest (
    @SerialName("companionId")
    val companionId: Long,
    @SerialName("userId")
    val userId: Long
)

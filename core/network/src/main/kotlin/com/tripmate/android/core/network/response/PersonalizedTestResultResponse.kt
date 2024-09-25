package com.tripmate.android.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonalizedTestResultResponse(
    @SerialName("data")
    val data: PersonalizedTestResult,
)

@Serializable
data class PersonalizedTestResult(
    @SerialName("characterType")
    val characterType: String,
)

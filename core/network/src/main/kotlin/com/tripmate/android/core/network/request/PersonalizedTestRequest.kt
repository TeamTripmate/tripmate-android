package com.tripmate.android.core.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonalizedTestRequest(
    @SerialName("mbti")
    val mbti: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("birthDate")
    val birthDate: String,
    @SerialName("keywords")
    val keywords: List<String>,
)

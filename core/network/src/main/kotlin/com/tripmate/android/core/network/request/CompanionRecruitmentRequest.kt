package com.tripmate.android.core.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanionRecruitmentRequest(
    @SerialName("spotId")
    val spotId: Int,
    @SerialName("date")
    val date: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("type")
    val type: String,
    @SerialName("someGenderYn")
    val sameGenderYn: Boolean,
    @SerialName("someAgeYn")
    val sameAgeYn: Boolean,
    @SerialName("openChatLink")
    val openChatLink: String,
    @SerialName("creatorId")
    val creatorId: Long,
)

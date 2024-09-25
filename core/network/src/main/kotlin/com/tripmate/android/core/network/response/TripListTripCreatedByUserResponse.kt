package com.tripmate.android.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanionResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: CompanionData,
    @SerialName("error")
    val error: String? = null
)

@Serializable
data class CompanionData(
    @SerialName("companions")
    val companions: List<CompanionInfo>
)

@Serializable
data class CompanionInfo(
    @SerialName("companionId")
    val companionId: Long,
    @SerialName("title")
    val title: String,
    @SerialName("date")
    val date: String,
    @SerialName("companionStatus")
    val companionStatus: String,
    @SerialName("applicantInfo")
    val applicantInfo: ApplicantInfo
)

@Serializable
data class ApplicantInfo(
    @SerialName("userId")
    val userId: Long,
    @SerialName("selectedKeyword")
    val selectedKeyword: List<String>,
    @SerialName("tripStyle")
    val tripStyle: String,
    @SerialName("characterId")
    val characterId: String
)

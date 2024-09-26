package com.tripmate.android.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatedTripListResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: CreatedTripList,
    @SerialName("error")
    val error: String? = null,
)

@Serializable
data class CreatedTripList(
    @SerialName("companions")
    val companions: List<CreatedCompanionInfo>,
)

@Serializable
data class CreatedCompanionInfo(
    @SerialName("companionId")
    val companionId: Long,
    @SerialName("title")
    val title: String,
    @SerialName("date")
    val date: String,
    @SerialName("companionStatus")
    val companionStatus: String,
    @SerialName("applicantInfo")
    val applicantInfo: ApplicantInfo,
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
    val characterId: String,
)

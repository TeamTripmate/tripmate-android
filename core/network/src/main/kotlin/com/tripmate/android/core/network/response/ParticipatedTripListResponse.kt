package com.tripmate.android.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParticipatedTripListResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: List<ParticipatedCompanionInfo>,
    @SerialName("error")
    val error: String?,
)

@Serializable
data class ParticipatedCompanionInfo(
    @SerialName("companionId")
    val companionId: Long,
    @SerialName("title")
    val title: String,
    @SerialName("date")
    val date: String,
    @SerialName("openChatLink")
    val openChatLink: String,
    @SerialName("reviewYn")
    val reviewYn: Boolean,
    @SerialName("matchingStatus")
    val matchingStatus: String,
    @SerialName("tripHostInfo")
    val tripHostInfo: TripHostInfo,
)

@Serializable
data class TripHostInfo(
    @SerialName("selectedKeyword")
    val selectedKeyword: List<String>,
    @SerialName("tripStyle")
    val tripStyle: String,
    @SerialName("characterId")
    val characterId: String,
)

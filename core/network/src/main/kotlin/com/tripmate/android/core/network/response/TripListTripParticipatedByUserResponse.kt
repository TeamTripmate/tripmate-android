package com.tripmate.android.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanionInfoNetworkResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: CompanionDataNetwork,
    @SerialName("error")
    val error: String?,
)

@Serializable
data class CompanionDataNetwork(
    @SerialName("companions")
    val companions: List<CompanionInfoNetwork>,
)

@Serializable
data class CompanionInfoNetwork(
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
    val tripHostInfo: TripHostInfoNetwork,
)

@Serializable
data class TripHostInfoNetwork(
    @SerialName("selectedKeyword")
    val selectedKeyword: List<String>,
    @SerialName("tripStyle")
    val tripStyle: String,
    @SerialName("characterId")
    val characterId: String,
)

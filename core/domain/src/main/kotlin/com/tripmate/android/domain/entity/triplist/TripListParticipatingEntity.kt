package com.tripmate.android.domain.entity.triplist

import androidx.compose.runtime.Stable

// 내가 참여한 여행 상태 리스트
@Stable
data class CompanionInfoEntity(
    val companionId: Long,
    val title: String,
    val date: String,
    val openChatLink: String,
    val reviewYn: Boolean,
    val matchingStatus: String,
    val tripHostInfo: TripHostInfo
)

data class TripHostInfo(
    val selectedKeyword: List<String>,
    val tripStyle: String,
    val characterId: String
)

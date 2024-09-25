package com.tripmate.android.domain.entity.triplist

import androidx.compose.runtime.Stable

// 내가 만든 여행 상태 리스트
@Stable
data class CompanionEntity(
    val companionId: Long,
    val title: String,
    val date: String,
    val companionStatus: String,
    val applicantInfo: Applicant
)

data class Applicant(
    val userId: Long,
    val selectedKeyword: List<String>,
    val tripStyle: String,
    val characterId: String
)

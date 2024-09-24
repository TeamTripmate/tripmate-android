package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class MateRecruitPostEntity(
    val spotId: String = "",
    val title: String = "",
    val date: String = "",
    val chatLink: String = "",
    val accompanyYn: Boolean = false,
    val description: String = "",
    val gender: String = "",
    val ageRange: String = "",
    val matchingRatio: String = "",
    val hostInfo: UserInfoEntity = UserInfoEntity(),
    val reviewRanks: List<String> = emptyList(),
    val mateRecruitPostReviewList: List<TripDetailMateReviewEntity> = emptyList(),
)

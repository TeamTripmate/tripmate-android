package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class MateRecruitPostEntity(
    val id: Int = 0,
    val mateRecruit: TripDetailMateRecruitEntity = TripDetailMateRecruitEntity(),
    val mateRecruitPostReviewAdvantage: List<String> = emptyList(),
    val mateRecruitPostReviewList: List<TripDetailMateReviewEntity> = emptyList(),
    val isAvailableMateRequest: Boolean = false,
)

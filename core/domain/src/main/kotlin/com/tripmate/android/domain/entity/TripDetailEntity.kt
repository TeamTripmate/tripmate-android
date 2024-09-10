package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class TripDetailEntity(
    val id: Int = 0,
    val title: String = "",
    val address: String = "",
    val description: String = "",
    val tipDescription: String = "",
    val tripIntroduceDescription: String = "",
    val tripDetailPhone: String = "",
    val tripDetailAddress: String = "",
    val tripDetailFee: String = "",
    val tripRecommendStyleEntity: List<TripDetailStyleEntity> = emptyList(),
    val tripDetailMateRecruit: List<TripDetailMateRecruitEntity> = emptyList(),
    val tripDetailMateReviewAdvantage: List<String> = emptyList(),
    val tripDetailMateReviewList: List<TripDetailMateReviewEntity> = emptyList(),
)

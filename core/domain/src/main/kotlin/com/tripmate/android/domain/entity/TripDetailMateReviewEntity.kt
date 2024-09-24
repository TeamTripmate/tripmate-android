package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class TripDetailMateReviewEntity(
    val userInfo: UserInfoEntity,
    val reviewDate: String = "",
    val likeList: List<String> = emptyList(),
)

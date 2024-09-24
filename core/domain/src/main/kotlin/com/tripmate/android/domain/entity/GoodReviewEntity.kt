package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class GoodReviewEntity(
    val id: Int = 0,
    val textResId: Int = 0,
    val isSelected: Boolean = false,
)

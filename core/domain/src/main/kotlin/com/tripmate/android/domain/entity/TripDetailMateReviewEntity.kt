package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class TripDetailMateReviewEntity(
    val id: Int = 0,
    val imageResId: Int = 0,
    val mateName: String = "",
    val mateStyleName: String = "",
    val mateReviewDate: String = "",
    val imageReviewUrl: String = "",
    val mateReviewDescription: String = "",
    val mateReviewType: List<String> = emptyList(),
)

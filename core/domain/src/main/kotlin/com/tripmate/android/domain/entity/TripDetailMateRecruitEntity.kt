package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class TripDetailMateRecruitEntity(
    val id: Int = 0,
    val imageResId: Int = 0,
    val mateName: String = "",
    val mateStyleName: String = "",
    val mateMatchingRatio: String = "",
    val mateStyleType: List<String> = emptyList(),
    val mateRecruitTitle: String = "",
    val mateRecruitDescription: String = "",
)

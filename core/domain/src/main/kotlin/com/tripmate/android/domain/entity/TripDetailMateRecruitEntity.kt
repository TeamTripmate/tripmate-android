package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class TripDetailMateRecruitEntity(
    val companionId: Long = 0,
    val imageResId: Int = 0,
    val profileImage: String = "",
    val mateName: String = "",
    val mateStyleName: String = "",
    val mateMatchingRatio: String = "",
    val mateStyleType: List<String> = emptyList(),
    val mateRecruitTitle: String = "",
    val mateRecruitDescription: String = "",
    val mateRecruitSubDescription: String = "",
    val mateRecruitAddress: String = "",
    val date: String = "",
    val mateRecruitDate: String = "",
    val gender: String = "",
    val ageRange: String = "",
)

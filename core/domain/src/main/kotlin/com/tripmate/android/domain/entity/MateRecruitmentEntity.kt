package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class MateRecruitmentEntity(
    val spotId: Int,
    val date: String,
    val title: String,
    val description: String,
    val type: String,
    val sameGenderYn: Boolean,
    val sameAgeYn: Boolean,
    val openChatLink: String,
    val creatorId: Long,
)

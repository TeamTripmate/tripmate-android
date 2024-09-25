package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class PersonalizedTestEntity(
    val mbti: String,
    val gender: String,
    val birthDate: String,
    val keywords: List<String>,
)

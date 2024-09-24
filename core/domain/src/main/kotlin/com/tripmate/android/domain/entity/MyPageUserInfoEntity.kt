package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class MyPageUserInfoEntity(
    val selectedKeyword: List<String>,
    val characterId: Long,
    val tripStyle: String,
)

package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class UserInfoEntity(
    val selectedKeyword: String,
    val characterId: Long,
    val tripStyle: String,
)

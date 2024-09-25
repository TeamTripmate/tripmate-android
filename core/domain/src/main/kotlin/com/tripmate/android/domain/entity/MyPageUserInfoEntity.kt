package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class MyPageUserInfoEntity(
    val selectedKeyword: List<String>,
    val characterId: String,
    val tripStyle: String,
    val nickname: String,
    val thumbnailImageUrl: String,
    val profileImageUrl: String,
)

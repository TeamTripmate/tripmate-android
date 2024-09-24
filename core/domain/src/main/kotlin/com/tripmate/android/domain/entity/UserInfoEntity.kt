package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class UserInfoEntity(
    val profileImage: String = "",
    val kakaoNickname: String = "",
    val characterName: String = "",
    val styleType: List<String> = emptyList(),
)

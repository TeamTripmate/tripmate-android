package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class MyPickEntity(
    val id: Int,
    val title: String,
    val description: String,
    val spotType: String,
    val thumbnailUrl: String,
    val latitude: Double,
    val longitude: Double,
    val distance: String,
    val address: String,
    val isLiked: Boolean,
    val tapType: String,
)

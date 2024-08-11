package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class TripStyleEntity(
    val id: Int,
    val text: String,
    val imageResId: Int,
    val isSelected: Boolean
)

package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class TripStyleEntity(
    val id: Int = 0,
    val textResId: Int = 0,
    val imageResId: Int = 0,
    val isSelected: Boolean = false,
    val keyword: String = "",
)

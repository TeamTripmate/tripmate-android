package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class TripDetailStyleEntity(
    val id: Int = 0,
    val text: String = "",
    val imageResId: Int = 0,
)

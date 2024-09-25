package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class ReportReasonEntity(
    val id: Int = 0,
    val textResId: Int = 0,
    val isSelected: Boolean = false,
)
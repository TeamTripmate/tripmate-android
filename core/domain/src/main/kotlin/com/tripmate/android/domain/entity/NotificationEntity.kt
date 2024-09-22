package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class NotificationEntity(
    val id: Long,
    val title: String,
    val message: String,
    val receivedDate: String,
    val receivedTime: String,
    val isRead: Boolean,
)

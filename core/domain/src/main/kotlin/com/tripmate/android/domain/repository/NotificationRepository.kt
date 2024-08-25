package com.tripmate.android.domain.repository

import com.tripmate.android.domain.entity.NotificationEntity

interface NotificationRepository {
    suspend fun getAllNotificationList(): Result<List<NotificationEntity>>
}

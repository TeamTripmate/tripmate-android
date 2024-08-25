package com.tripmate.android.notification.viewmodel

import com.tripmate.android.domain.entity.NotificationEntity

sealed interface NotificationUiAction {
    data class OnNotificationItemClick(val notification: NotificationEntity) : NotificationUiAction
    data class OnRetryClick(val error: ErrorType) : NotificationUiAction
}

enum class ErrorType {
    NETWORK,
    SERVER,
}

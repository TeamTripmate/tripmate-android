package com.tripmate.android.notification.viewmodel

import com.tripmate.android.domain.entity.NotificationEntity

sealed interface NotificationUiEvent {
    data class NavigateToDetail(val notification: NotificationEntity) : NotificationUiEvent
}

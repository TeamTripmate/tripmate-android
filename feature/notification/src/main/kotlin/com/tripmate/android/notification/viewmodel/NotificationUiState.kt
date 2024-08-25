package com.tripmate.android.notification.viewmodel

import com.tripmate.android.domain.entity.NotificationEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class NotificationUiState(
    val notificationList: ImmutableList<Pair<String, List<NotificationEntity>>> = persistentListOf(),
    val isNetworkErrorDialogVisible: Boolean = false,
    val isServerErrorDialogVisible: Boolean = false,
)

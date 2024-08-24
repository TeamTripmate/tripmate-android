package com.tripmate.android.notification.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.core.common.ErrorHandlerActions
import com.tripmate.android.core.common.handleException
import com.tripmate.android.domain.entity.NotificationEntity
import com.tripmate.android.domain.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository,
) : ViewModel(), ErrorHandlerActions {
    private val _uiState = MutableStateFlow(NotificationUiState())
    val uiState: StateFlow<NotificationUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<NotificationUiEvent>()
    val uiEvent: Flow<NotificationUiEvent> = _uiEvent.receiveAsFlow()

    init {
        getAllNotificationList()
    }

    fun onAction(action: NotificationUiAction) {
        when (action) {
            is NotificationUiAction.OnNotificationItemClick -> updateNotification(action.notification)
            is NotificationUiAction.OnRetryClick -> refresh(action.error)
        }
    }

    private fun getAllNotificationList() {
        viewModelScope.launch {
            notificationRepository.getAllNotificationList()
                .onSuccess { notifications ->
                    val groupedNotifications = groupNotificationsByDate(notifications)
                    _uiState.update {
                        it.copy(notificationList = groupedNotifications)
                    }
                }
                .onFailure { exception ->
                    handleException(exception, this@NotificationViewModel)
                }
        }
    }

    private fun groupNotificationsByDate(notifications: List<NotificationEntity>): ImmutableList<Pair<String, List<NotificationEntity>>> {
        return notifications
            .groupBy { it.receivedDate }
            .toList()
            .toImmutableList()
    }

    private fun updateNotification(selectedNotification: NotificationEntity) {
        _uiState.update {
            it.copy(
                notificationList = it.notificationList.map { (date, notifications) ->
                    date to notifications.map { notification ->
                        if (notification.id == selectedNotification.id) {
                            notification.copy(isRead = true)
                        } else {
                            notification
                        }
                    }
                }.toImmutableList(),
            )
        }
    }

    override fun setServerErrorDialogVisible(flag: Boolean) {
        _uiState.update {
            it.copy(isServerErrorDialogVisible = flag)
        }
    }

    override fun setNetworkErrorDialogVisible(flag: Boolean) {
        _uiState.update {
            it.copy(isServerErrorDialogVisible = flag)
        }
    }

    private fun refresh(error: ErrorType) {
        getAllNotificationList()
        when (error) {
            ErrorType.NETWORK -> setNetworkErrorDialogVisible(false)
            ErrorType.SERVER -> setServerErrorDialogVisible(false)
        }
    }
}

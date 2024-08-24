package com.tripmate.android.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.component.NetworkErrorDialog
import com.tripmate.android.core.designsystem.component.ServerErrorDialog
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.domain.entity.NotificationEntity
import com.tripmate.android.notification.component.EmptyNotificationItem
import com.tripmate.android.notification.component.NotificationItem
import com.tripmate.android.notification.viewmodel.ErrorType
import com.tripmate.android.notification.viewmodel.NotificationUiAction
import com.tripmate.android.notification.viewmodel.NotificationUiState
import com.tripmate.android.notification.viewmodel.NotificationViewModel
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun NotificationRoute(
    innerPadding: PaddingValues,
    viewModel: NotificationViewModel = hiltViewModel(),
) {
    val notificationUiState by viewModel.uiState.collectAsStateWithLifecycle()

    NotificationScreen(
        innerPadding = innerPadding,
        notificationUiState = notificationUiState,
        onAction = viewModel::onAction,
    )
}

@Composable
fun NotificationScreen(
    innerPadding: PaddingValues,
    notificationUiState: NotificationUiState,
    onAction: (NotificationUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background02)
            .padding(innerPadding),
    ) {
        Column {
            TripmateTopAppBar(
                navigationType = TopAppBarNavigationType.None,
                title = stringResource(id = R.string.notification_title),
            )
            LazyColumn {
                item { Spacer(modifier = Modifier.height(5.dp)) }
                if (notificationUiState.notificationList.isEmpty()) {
                    item {
                        EmptyNotificationItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(248.dp),
                        )
                    }
                } else {
                    notificationUiState.notificationList.forEach { (date, notifications) ->
                        item {
                            Text(
                                text = date,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Background02)
                                    .padding(top = 16.dp, start = 16.dp, bottom = 8.dp, end = 16.dp),
                                style = Medium16_SemiBold,
                                color = Color.Black,
                            )
                        }
                        itemsIndexed(
                            items = notifications,
                            key = { _, notification -> notification.id },
                        ) { index, notification ->
                            NotificationItem(
                                notification = notification,
                                modifier = Modifier.clickable {
                                    onAction(NotificationUiAction.OnNotificationItemClick(notification))
                                },
                            )
                            if (index < notifications.size - 1) {
                                Spacer(modifier = Modifier.height(2.dp))
                            }
                        }
                    }
                }
            }
        }
        if (notificationUiState.isServerErrorDialogVisible) {
            ServerErrorDialog(
                onRetryClick = { onAction(NotificationUiAction.OnRetryClick(ErrorType.SERVER)) },
            )
        }

        if (notificationUiState.isNetworkErrorDialogVisible) {
            NetworkErrorDialog(
                onRetryClick = { onAction(NotificationUiAction.OnRetryClick(ErrorType.NETWORK)) },
            )
        }
    }
}

@DevicePreview
@Composable
fun NotificationScreenPreview() {
    TripmateTheme {
        NotificationScreen(
            innerPadding = PaddingValues(0.dp),
            notificationUiState = NotificationUiState(
                notificationList = persistentListOf(
                    Pair(
                        "2024.6.24",
                        persistentListOf(
                            NotificationEntity(
                                id = 0L,
                                title = "여행은 즐거우셨나요?",
                                message = "동행 일정이 완료되었습니다.\n함께 동행한 동행자는 어떠셨나요?",
                                receivedDate = "2024.6.24",
                                receivedTime = "20:32",
                                isRead = false,
                            ),
                            NotificationEntity(
                                id = 1L,
                                title = "여행은 즐거우셨나요?",
                                message = "동행 일정이 완료되었습니다.\n함께 동행한 동행자는 어떠셨나요?",
                                receivedDate = "2024.6.24",
                                receivedTime = "20:33",
                                isRead = true,
                            ),
                        ),
                    ),
                    Pair(
                        "2024.6.23",
                        persistentListOf(
                            NotificationEntity(
                                id = 2L,
                                title = "새로운 동행 요청이 도착했어요!",
                                message = "새로운 동행 요청을 확인해보세요.",
                                receivedDate = "2024.6.23",
                                receivedTime = "15:45",
                                isRead = false,
                            ),
                        ),
                    ),
                ),
            ),
            onAction = {},
        )
    }
}

@DevicePreview
@Composable
fun EmptyNotificationScreenPreview() {
    TripmateTheme {
        NotificationScreen(
            innerPadding = PaddingValues(0.dp),
            notificationUiState = NotificationUiState(
                notificationList = persistentListOf(),
            ),
            onAction = {},
        )
    }
}

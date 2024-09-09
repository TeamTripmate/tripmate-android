package com.tripmate.android.notification.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Background03
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Gray005
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.domain.entity.NotificationEntity

@Composable
internal fun NotificationItem(
    notification: NotificationEntity,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.background(
            if (notification.isRead) Background02
            else Background03,
        ),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = notification.title,
                    color = Gray001,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = Medium16_SemiBold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notification.message,
                    color = Gray004,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = Small14_Reg,
                )
            }
            Text(
                text = notification.receivedTime,
                color = Gray005,
                style = XSmall12_Reg,
            )
        }
    }
}

@ComponentPreview
@Composable
private fun UnReadNotificationItemPreview() {
    TripmateTheme {
        NotificationItem(
            notification = NotificationEntity(
                id = 0L,
                title = "여행은 즐거우셨나요?",
                message = "동행 일정이 완료되었습니다.\n함께 동행한 동행자는 어떠셨나요?",
                receivedDate = "2024.6.24",
                receivedTime = "20:32",
                isRead = false,
            ),
        )
    }
}

@ComponentPreview
@Composable
private fun ReadNotificationItemPreview() {
    TripmateTheme {
        NotificationItem(
            notification = NotificationEntity(
                id = 0L,
                title = "여행은 즐거우셨나요?",
                message = "동행 일정이 완료되었습니다.\n함께 동행한 동행자는 어떠셨나요?",
                receivedDate = "2024.6.24",
                receivedTime = "20:32",
                isRead = true,
            ),
        )
    }
}

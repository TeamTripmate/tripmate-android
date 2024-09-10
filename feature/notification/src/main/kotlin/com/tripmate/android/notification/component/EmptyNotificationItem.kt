package com.tripmate.android.notification.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray005
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.TripmateTheme

@Composable
internal fun EmptyNotificationItem(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.background(Background02),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.notification_empty),
                style = Medium16_Mid,
                color = Gray005,
            )
        }
    }
}

@ComponentPreview
@Composable
private fun EmptyNotificationItemPreview() {
    TripmateTheme {
        EmptyNotificationItem(
            modifier = Modifier.fillMaxSize(),
        )
    }
}

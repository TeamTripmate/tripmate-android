package com.tripmate.android.feature.trip_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.trip_list.component.Ticket
import com.tripmate.android.feature.trip_list.preview.MateSelectPreviewParameterProvider
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiAction
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiState


@Composable
fun MateListScreen(
    uiState: TripListUiState,
    innerPadding: PaddingValues,
    onAction: (TripListUiAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TripmateTopAppBar(
            navigationType = TopAppBarNavigationType.Back,
            title = "신청자 목록",
            onNavigationClick = { },
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "누구와 함께 여행가실래요?",
            style = Large20_Bold,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "동행인을 선택해주세요.",
            style = Small14_Reg,
        )
        Spacer(modifier = Modifier.height(32.dp))
        LazyColumn {
            itemsIndexed(
                items = uiState.ticket,
                key = { _, ticket -> ticket.ticketId },
            ) { ticketIndex, ticket ->
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    Ticket(
                        ticket = ticket,
                        ticketIndex = ticketIndex,
                        isTicketClicked = uiState.isTicketClicked[ticketIndex],
                        onAction = onAction
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        TripmateButton(
            onClick = { },
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .padding(start = 4.dp),
            containerColor = Primary01,
        ) {
            Text(
                text = "선택하기",
                color = Color.White,
                style = Medium16_SemiBold,
            )
        }
    }
}


@DevicePreview
@Composable
private fun MateListScreenPreview(
    @PreviewParameter(MateSelectPreviewParameterProvider::class)
    tripListUiState: TripListUiState,
) {
    TripmateTheme {
        MateListScreen(
            uiState = tripListUiState,
            innerPadding = PaddingValues(16.dp),
            onAction = {},
        )
    }
}


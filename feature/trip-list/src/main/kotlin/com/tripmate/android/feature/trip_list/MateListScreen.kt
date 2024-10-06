package com.tripmate.android.feature.trip_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_Reg
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.trip_list.component.Ticket
import com.tripmate.android.feature.trip_list.preview.MateSelectPreviewParameterProvider
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiAction
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiEvent
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiState
import com.tripmate.android.feature.trip_list.viewmodel.TripListViewModel
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun MateListRoute(
    innerPadding: PaddingValues,
    popBackStack: () -> Unit,
    viewModel: TripListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is TripListUiEvent.NavigateBack -> popBackStack()
            else -> { }
        }
    }

    MateListScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@Composable
fun MateListScreen(
    uiState: TripListUiState,
    innerPadding: PaddingValues,
    onAction: (TripListUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp), // 버튼 높이 + 패딩을 고려한 여백
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TripmateTopAppBar(
                navigationType = TopAppBarNavigationType.Back,
                title = "신청자 목록",
                onNavigationClick = { onAction(TripListUiAction.OnBackClicked) },
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
            val currentCompanion = uiState.createdCompanionList.getOrNull(uiState.page)
            if (currentCompanion?.applicantInfoEntityInfo?.isNotEmpty() == true) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                ) {
                    itemsIndexed(
                        items = currentCompanion.applicantInfoEntityInfo,
                        key = { index, _ -> index },
                    ) { applicantIndex, applicant ->
                        val isClicked = uiState.selectedTicketIndex == applicantIndex
                        Column {
                            Spacer(modifier = Modifier.height(16.dp))
                            Ticket(
                                ticket = applicant,
                                ticketIndex = applicantIndex,
                                isTicketClicked = isClicked,
                                onAction = onAction,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("아직 신청자가 없어요", style = Medium16_Reg, color = Gray006)
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.White)
                .shadow(
                    elevation = 8.dp,
                    shape = RectangleShape,
                    spotColor = Color.Black.copy(alpha = 0.1f),
                ),
        ) {
            TripmateButton(
                onClick = { onAction(TripListUiAction.OnSelectMateClicked) },
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(48.dp),
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

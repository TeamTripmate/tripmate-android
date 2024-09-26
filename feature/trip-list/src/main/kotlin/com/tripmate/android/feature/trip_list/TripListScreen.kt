package com.tripmate.android.feature.trip_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.trip_list.component.PageIndicator
import com.tripmate.android.feature.trip_list.component.ReviewItems
import com.tripmate.android.feature.trip_list.component.TripStatusCard
import com.tripmate.android.feature.trip_list.component.TripStatusCardTeamLeader
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiAction
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiEvent
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiState
import com.tripmate.android.feature.trip_list.viewmodel.TripListViewModel
import kotlinx.coroutines.launch

@Composable
internal fun TripListRoute(
    innerPadding: PaddingValues,
    navigateToMateList: () -> Unit,
    navigateToMateOpenChat: () -> Unit,
    viewModel: TripListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is TripListUiEvent.NavigateToMateList -> navigateToMateList()
            is TripListUiEvent.NavigateToMateOpenChat -> navigateToMateOpenChat()
            else -> {}
        }
    }
    TripListScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TripListScreen(
    uiState: TripListUiState,
    innerPadding: PaddingValues,
    onAction: (TripListUiAction) -> Unit,
    viewModel: TripListViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()

    // 탭의 선택 상태를 관리하는 변수
    var selectedTabIndex by remember { mutableStateOf(0) }

    // 각 탭에 대한 별도의 HorizontalPagerState 정의 (예: 두 개의 탭)
    val horizontalPagerState1 = rememberPagerState(
        initialPage = 0,
        pageCount = { uiState.participatedCompanionList.size }, // 첫 번째 탭의 HorizontalPager 페이지 수
    )
    val horizontalPagerState2 = rememberPagerState(
        initialPage = 0,
        pageCount = { uiState.createdCompanionList.size }, // 두 번째 탭의 HorizontalPager 페이지 수
    )

    LaunchedEffect(selectedTabIndex) {
        if (selectedTabIndex == 0) {
            viewModel.getParticipatedTripList()
        } else {
            viewModel.getCreatedTripList()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.White),
    ) {
        // 상단 탭바
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            indicator = { tabPositions ->
                SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 2.dp,
                    color = Primary01,
                )
            },
            containerColor = Color.White,
        ) {
            uiState.tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        scope.launch {
                            when (index) {
                                0 -> horizontalPagerState1.animateScrollToPage(0)
                                1 -> horizontalPagerState2.animateScrollToPage(0)
                                // 추가 탭이 있다면 여기서 처리
                            }
                        }
                    },
                    text = {
                        Text(
                            text = title,
                            style = if (selectedTabIndex == index) Medium16_SemiBold else Medium16_Mid,
                            color = if (selectedTabIndex == index) Gray001 else Gray006,
                        )
                    },
                )
            }
        }

        // 각 탭에 따른 HorizontalPager 표시
        when (selectedTabIndex) {
            0 -> {
                if (uiState.participatedCompanionList.isNotEmpty()) {
                    HorizontalPager(
                        state = horizontalPagerState1,
                        modifier = Modifier.fillMaxWidth(),
                    ) { page ->
                        val companion = uiState.participatedCompanionList[page]
                        TripStatusCard(
                            title = companion.title,
                            date = companion.date,
                            matchingStatus = companion.matchingStatus,
                            selectedKeyword = companion.tripHostInfoEntity.selectedKeyword,
                            characterId = companion.tripHostInfoEntity.characterId,
                            modifier = Modifier.clickable {
                                onAction(
                                    TripListUiAction.OnTripStatusCardClicked(
                                        companion.openChatLink,
                                        companion.tripHostInfoEntity.selectedKeyword,
                                        companion.tripHostInfoEntity.characterId,
                                    ),
                                )
                            },
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    // HorizontalPager 인디케이터
                    PageIndicator(pagerState = horizontalPagerState1)
                } else {
                    // Handle empty state when there are no active trips
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp), // Adjust height as needed
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "활성화된 여행이 없습니다.",
                            style = Medium16_Mid,
                            color = Gray006,
                        )
                    }
                }
            }

            1 -> {
                if (uiState.createdCompanionList.isNotEmpty()) {
                    HorizontalPager(
                        state = horizontalPagerState2,
                        modifier = Modifier.fillMaxWidth(),
                    ) { page ->
                        val companion = uiState.createdCompanionList[page]
                        TripStatusCardTeamLeader(
                            title = companion.title,
                            date = companion.date,
                            companionStatus = companion.companionStatus,
                            appliedMateInfo = companion.applicantInfoEntityInfo,
                            onAction = onAction,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                // HorizontalPager 인디케이터
                PageIndicator(pagerState = horizontalPagerState2)
            }
            // 추가 탭이 있다면 여기서 처리
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
//            if (uiState.tripList.isEmpty()) {
//                item {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .wrapContentSize(Alignment.Center)
//                    ) {
//                        Text("동행 기록이 없어요", style = TextStyle(color = Gray003, fontSize = 16.sp))
//                    }
//                }
//            }
//            else {
            items(3) {
//                    index ->
//                    val trip = uiState.tripList[index]
                ReviewItems(
                    title = "서피비치에서 식사해요",
                    date = "2024.08.24(일) 11:00 AM",
                    isReviewPeriodOver = true,
                    onReviewClick = { /* 클릭 처리 */ },
                )
            }
//            }
        }
    }
}

@DevicePreview
@Composable
internal fun TripListPreview() {
    TripmateTheme {
        TripListScreen(
            innerPadding = PaddingValues(0.dp),
            uiState = TripListUiState(),
            onAction = {},
        )
    }
}

@ComponentPreview
@Composable
internal fun ReviewComponentPreview() {
    TripmateTheme {
        ReviewItems(
            title = "서피비치에서 식사해요",
            date = "2024.08.24(일) 11:00 AM",
            isReviewPeriodOver = false,
            onReviewClick = {},
        )
    }
}

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
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
import com.tripmate.android.feature.triplist.R
import kotlinx.coroutines.launch

@Composable
internal fun TripListRoute(
    innerPadding: PaddingValues,
    navigateToMateList: (Long, Int) -> Unit,
    navigateToMateOpenChat: (
        openChatLink: String,
        selectedKeyword1: String,
        selectedKeyword2: String,
        selectedKeyword3: String,
        tripStyle: String,
        characterId: String,
    ) -> Unit,
    viewModel: TripListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is TripListUiEvent.NavigateToMateList -> navigateToMateList(event.companionId, event.page)
            is TripListUiEvent.NavigateToMateOpenChat -> navigateToMateOpenChat(
                event.openChatLink,
                event.selectedKeyword1,
                event.selectedKeyword2,
                event.selectedKeyword3,
                event.tripStyle,
                event.characterId,
            )

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

    var selectedTabIndex by remember { mutableStateOf(0) }

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
        TripmateTopAppBar(
            navigationType = TopAppBarNavigationType.None,
            title = stringResource(R.string.trip_list),
        )
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

        when (selectedTabIndex) {
            0 -> {
                if (uiState.participatedCompanionList.isNotEmpty()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                                clip = true,
                            ),
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                    ) {
                        Column {
                            HorizontalPager(
                                state = horizontalPagerState1,
                                modifier = Modifier.fillMaxWidth(),
                            ) { page ->
                                val companion = uiState.participatedCompanionList[page]
                                TripStatusCard(
                                    title = companion.title,
                                    date = companion.date,
                                    matchingStatus = companion.matchingStatus,
                                    modifier = Modifier.clickable {
                                        onAction(
                                            TripListUiAction.OnTripStatusCardClicked(
                                                companion.openChatLink,
                                                companion.tripHostInfoEntity.selectedKeyword,
                                                companion.tripHostInfoEntity.tripStyle,
                                                companion.tripHostInfoEntity.characterId,
                                            ),
                                        )
                                    },
                                )
                            }
                            PageIndicator(pagerState = horizontalPagerState1)
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "동행 기록이 없어요",
                            style = Medium16_Mid,
                            color = Gray006,
                        )
                    }
                }
            }

            1 -> {
                if (uiState.createdCompanionList.isNotEmpty()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                                clip = true,
                            ),
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                    ) {
                        Column {
                            HorizontalPager(
                                state = horizontalPagerState2,
                                modifier = Modifier.fillMaxWidth(),
                            ) { page ->
                                val companion = uiState.createdCompanionList[page]
                                TripStatusCardTeamLeader(
                                    title = companion.title,
                                    date = companion.date,
                                    companionStatus = companion.companionStatus,
                                    companionId = companion.companionId,
                                    page = page,
                                    onAction = onAction,
                                )
                            }
                            PageIndicator(pagerState = horizontalPagerState2)
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "동행 기록이 없어요",
                            style = Medium16_Mid,
                            color = Gray006,
                        )
                    }
                }
            }
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
//            items(3) {
//                    index ->
//                    // val trip = uiState.tripList[index]
//                ReviewItems(
//                    title = "서피비치에서 식사해요",
//                    date = "2024.08.24(일) 11:00 AM",
//                    isReviewPeriodOver = true,
//                    onReviewClick = { /* 클릭 처리 */ },
//                )
//            }
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

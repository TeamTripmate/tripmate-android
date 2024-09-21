package com.tripmate.android.feature.trip_list

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.rememberCoroutineScope
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
    viewModel: TripListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { uiState.tabs.size },
    )
    val horizontalPagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 3 },  // 예를 들어 3개의 페이지가 있다고 가정
    )
    val scope = rememberCoroutineScope()

    // 탭 변경 시 호출되는 이벤트 처리
    LaunchedEffect(pagerState.currentPage) {
        onAction(TripListUiAction.OnTabChanged(pagerState.currentPage))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.White),
    ) {
        // 상단 탭바
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            indicator = { tabPositions ->
                SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    height = 2.dp,
                    color = Primary01,
                )
            },
            containerColor = Color.White,
        ) {
            uiState.tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = title,
                            style = if (pagerState.currentPage == index) Medium16_SemiBold else Medium16_Mid,
                            color = if (pagerState.currentPage == index) Gray001 else Gray006,
                        )
                    },
                )
            }
        }

        // 탭에 따라 다른 HorizontalPager 표시
        if (pagerState.currentPage == 0) {
            HorizontalPager(
                state = horizontalPagerState,
                modifier = Modifier.fillMaxWidth(),
            ) {
                TripStatusCard(pagerState = pagerState)
            }
        } else if (pagerState.currentPage == 1) {
            HorizontalPager(
                state = horizontalPagerState,
                modifier = Modifier.fillMaxWidth(),
            ) {
                TripStatusCardTeamLeader(
                    pagerState = pagerState,
                    onAction = onAction,
                )
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


@ComponentPreview
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

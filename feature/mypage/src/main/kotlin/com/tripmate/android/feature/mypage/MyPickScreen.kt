package com.tripmate.android.feature.mypage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.mypage.component.MyPickItem
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageUiAction
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageUiEvent
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageUiState
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageViewModel
import kotlinx.coroutines.launch

@Composable
internal fun MyPickRoute(
    innerPadding: PaddingValues,
    popBackStack: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is MyPageUiEvent.NavigateBack -> popBackStack()
            else -> {}
        }
    }

    MyPickScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MyPickScreen(
    uiState: MyPageUiState,
    innerPadding: PaddingValues,
    onAction: (MyPageUiAction) -> Unit,
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { uiState.tabs.size },
    )
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        onAction(MyPageUiAction.OnTabChanged(pagerState.currentPage))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp),
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth(),
            indicator = { tabPositions ->
                SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    height = 2.dp,
                    color = Primary01,
                )
            },
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

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            pageSpacing = 32.dp,
        ) { page ->
            ContentForTab(
                tabIndex = page,
                uiState = uiState,
                onAction = onAction,
            )
        }
    }
}

@Suppress("UnusedParameter")
@Composable
private fun ContentForTab(
    tabIndex: Int,
    uiState: MyPageUiState,
    onAction: (MyPageUiAction) -> Unit,
) {
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(40.dp),
        ) {
            items(5) {
                MyPickItem(
                    locationTag = "양양",
                    categoryTag = "서핑",
                    mateTag = "액티비티 동행",
                    imgUrl = "https://picsum.photos/36",
                    title = "양양 서핑 체험",
                    description = "양양 서핑 체험을 통해 새로운 경험을 즐겨보세요!",
                    location = "강원도 양양군",
                )
            }
        }
    }
}

@DevicePreview
@Composable
internal fun HomeScreenPreview() {
    TripmateTheme {
        MyPickScreen(
            innerPadding = PaddingValues(16.dp),
            uiState = MyPageUiState(),
            onAction = {},
        )
    }
}

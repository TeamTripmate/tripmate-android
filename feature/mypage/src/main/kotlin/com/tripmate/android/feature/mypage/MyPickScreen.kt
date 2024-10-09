package com.tripmate.android.feature.mypage

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.mypage.component.MyPickItem
import com.tripmate.android.feature.mypage.viewmodel.MyPageUiAction
import com.tripmate.android.feature.mypage.viewmodel.MyPageUiEvent
import com.tripmate.android.feature.mypage.viewmodel.MyPageUiState
import com.tripmate.android.feature.mypage.viewmodel.MyPickViewModel
import kotlinx.coroutines.launch

@Composable
internal fun MyPickRoute(
    innerPadding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToTripDetail: (String) -> Unit,
    viewModel: MyPickViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is MyPageUiEvent.NavigateBack -> popBackStack()
            is MyPageUiEvent.NavigateToTripDetail -> navigateToTripDetail(event.spotId.toString())
            else -> {}
        }
    }

    MyPickScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun MyPickScreen(
    uiState: MyPageUiState,
    innerPadding: PaddingValues,
    onAction: (MyPageUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Column {
            TripmateTopAppBar(
                navigationType = TopAppBarNavigationType.Back,
                title = stringResource(R.string.my_pick),
                onNavigationClick = {
                    onAction(MyPageUiAction.OnBackClicked)
                },
            )
            MyPickContent(
                uiState = uiState,
                onAction = onAction,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MyPickContent(
    uiState: MyPageUiState,
    onAction: (MyPageUiAction) -> Unit,
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { uiState.tabs.size },
    )

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
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
        Spacer(modifier = Modifier.height(24.dp))
        HorizontalPager(
            state = pagerState,
        ) { page ->
            ContentForTab(
                tabIndex = page,
                uiState = uiState,
                onAction = onAction,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Suppress("UnusedParameter")
@Composable
private fun ContentForTab(
    tabIndex: Int,
    uiState: MyPageUiState,
    onAction: (MyPageUiAction) -> Unit,
) {
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            items(
                count = uiState.myPickList.size,
                key = { index -> uiState.myPickList[index].id },
            ) { index ->
                MyPickItem(
                    imgUrl = uiState.myPickList[index].thumbnailUrl,
                    title = uiState.myPickList[index].title,
                    location = uiState.myPickList[index].address,
                    onHeartClicked = { onAction(MyPageUiAction.OnHeartClicked(uiState.myPickList[index])) },
                    modifier = Modifier
                        .animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = LinearOutSlowInEasing,
                            ),
                        )
                        .clickable {
                            onAction(MyPageUiAction.OnMyPickItemClicked(uiState.myPickList[index].id))
                        },
                )
            }
        }
    }
}

@DevicePreview
@Composable
internal fun MyPickScreenPreview() {
    TripmateTheme {
        MyPickScreen(
            innerPadding = PaddingValues(),
            uiState = MyPageUiState(),
            onAction = {},
        )
    }
}

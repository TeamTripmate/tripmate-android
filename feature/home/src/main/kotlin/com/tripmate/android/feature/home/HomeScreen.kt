package com.tripmate.android.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.home.component.HomeFilterChips
import com.tripmate.android.feature.home.component.HomeItem
import com.tripmate.android.feature.home.component.TripOriginal
import com.tripmate.android.feature.home.viewmodel.HomeUiAction
import com.tripmate.android.feature.home.viewmodel.HomeUiState
import com.tripmate.android.feature.home.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
internal fun HomeRoute(
    innerPadding: PaddingValues,
    navigateToMateRecruit: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToMateReview: () -> Unit,
    navigateToTripDetail: (spotId: String) -> Unit,
    navigateToMateReviewPost: (Int) -> Unit,
    navigateToReport: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        navigateToMateRecruit = navigateToMateRecruit,
        onAction = viewModel::onAction,
        navigateToMateReview = navigateToMateReview,
        navigateToTripDetail = navigateToTripDetail,
        navigateToMateReviewPost = navigateToMateReviewPost,
        navigateToReport = navigateToReport,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Suppress("UnusedParameter")
@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    innerPadding: PaddingValues,
    navigateToMateRecruit: () -> Unit,
    onAction: (HomeUiAction) -> Unit,
    navigateToMateReview: () -> Unit,
    navigateToTripDetail: (spotId: String) -> Unit,
    navigateToMateReviewPost: (Int) -> Unit,
    navigateToReport: () -> Unit,
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { uiState.tabs.size },
    )
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        onAction(HomeUiAction.OnTabChanged(pagerState.currentPage))
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
                navigateToMateRecruit = navigateToMateRecruit,
                navigateToTripDetail = navigateToTripDetail,
                navigateToReport = navigateToReport,
            )
        }
    }
}

@Suppress("UnusedParameter")
@Composable
private fun ContentForTab(
    tabIndex: Int,
    uiState: HomeUiState,
    onAction: (HomeUiAction) -> Unit,
    navigateToMateRecruit: () -> Unit,
    navigateToTripDetail: (spotId: String) -> Unit,
    navigateToReport: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        HomeFilterChips(
            onChipClick = { onAction(HomeUiAction.OnClickChip(it)) },
            selectedChips = if (tabIndex == 0) uiState.activitySelectedChips else uiState.healingSelectedChips,
            tabIndex = tabIndex,
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (uiState.showTripOriginal) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(40.dp),
            ) {
                items(uiState.tripOriginalList.size) { index ->
                    val tripOriginal = uiState.tripOriginalList[index]

                    TripOriginal(
                        imgUrl = tripOriginal.imgUrl,
                        title = tripOriginal.title,
                        description = tripOriginal.description,
                        location = tripOriginal.location,
                        time = tripOriginal.time,
                    )
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(40.dp),
            ) {
                items(uiState.spotList.size) { index ->
                    val spot = uiState.spotList[index]
                    val locationTag = spot.address.split(" ").getOrNull(1) ?: ""
                    val mateTag = if (spot.companionYn) {
                        if (tabIndex == 0) "액티비티 동행" else "힐링 동행"
                    } else null // 동행모집이 없으면 mateTag는 null

                    HomeItem(
                        locationTag = locationTag,
                        categoryTag = spot.spotType,
                        mateTag = mateTag, // mateTag가 null일 경우 표시되지 않음
                        imgUrl = spot.thumbnailUrl,
                        title = spot.title,
                        description = spot.description,
                        location = spot.address,
                        modifier = Modifier.clickable {
                            navigateToTripDetail(spot.id.toString())
                        },
                    )
                }
            }
        }
    }
}

@DevicePreview
@Composable
internal fun HomeScreenPreview() {
    TripmateTheme {
        HomeScreen(
            innerPadding = PaddingValues(16.dp),
            navigateToMateRecruit = {},
            uiState = HomeUiState(),
            onAction = {},
            navigateToMateReview = {},
            navigateToTripDetail = {},
            navigateToMateReviewPost = {},
            navigateToReport = {},
        )
    }
}

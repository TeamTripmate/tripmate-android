package com.tripmate.android.feature.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.home.component.HomeFilterChips
import com.tripmate.android.feature.home.component.HomeItem
import com.tripmate.android.feature.home.viewmodel.HomeUiAction
import com.tripmate.android.feature.home.viewmodel.HomeUiState
import com.tripmate.android.feature.home.viewmodel.HomeViewModel

@Composable
internal fun HomeRoute(
    innerPadding: PaddingValues,
    navigateToMateRecruit: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        navigateToMateRecruit = navigateToMateRecruit,
        onAction = viewModel::onAction,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    innerPadding: PaddingValues,
    navigateToMateRecruit: () -> Unit,
    onAction: (HomeUiAction) -> Unit,
) {
    val pagerState = rememberPagerState(
        pageCount = { uiState.tabs.size }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp),
    ) {
        TabRow(
            selectedTabIndex = uiState.selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            indicator = { tabPositions ->
                SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[uiState.selectedTabIndex]),
                    height = 2.dp,
                    color = Primary01
                )
            }
        ) {
            uiState.tabs.forEachIndexed { index, title ->
                Tab(
                    selected = uiState.selectedTabIndex == index,
                    onClick = { onAction(HomeUiAction.OnClickTab(index)) },
                    text = {
                        Text(
                            text = title,
                            style = if (uiState.selectedTabIndex == index) Medium16_SemiBold else Medium16_Mid,
                            color = if (uiState.selectedTabIndex == index) Gray001 else Gray006
                        )
                    },
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            key = { uiState.selectedTabIndex }
        ) { page ->
            ContentForTab(
                tabIndex = page,
                uiState = uiState,
                onAction = onAction,
                navigateToMateRecruit = navigateToMateRecruit
            )
        }

    }
}


@Composable
private fun ContentForTab(
    tabIndex: Int,
    uiState: HomeUiState,
    onAction: (HomeUiAction) -> Unit,
    navigateToMateRecruit: () -> Unit,
) {
    Column {
        HomeFilterChips(
            onChipClick = { onAction(HomeUiAction.OnClickChip(it)) },
            selectedChips = uiState.selectedChips,
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(40.dp),
        ) {
            items(5) {
                HomeItem()
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TripmateButton(
            onClick = navigateToMateRecruit,
        ) {
            Text(text = if (tabIndex == 0) "액티비티 모집" else "힐링 모집")
        }
    }
}

@DevicePreview
@Composable
internal fun HomeScreenPreview() {
    HomeScreen(
        innerPadding = PaddingValues(16.dp),
        navigateToMateRecruit = {},
        uiState = HomeUiState(),
        onAction = {},
    )
}

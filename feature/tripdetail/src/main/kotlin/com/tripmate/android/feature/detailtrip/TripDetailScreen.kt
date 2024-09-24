package com.tripmate.android.feature.detailtrip

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.component.NetworkImage
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray003
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.Tips
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.detailtrip.component.DetailInfoTab
import com.tripmate.android.feature.detailtrip.component.MateRecruitTab
import com.tripmate.android.feature.detailtrip.viewmodel.TripDetailUiAction
import com.tripmate.android.feature.detailtrip.viewmodel.TripDetailUiEvent
import com.tripmate.android.feature.detailtrip.viewmodel.TripDetailUiState
import com.tripmate.android.feature.detailtrip.viewmodel.TripDetailViewModel
import kotlinx.coroutines.launch

@Composable
fun TripDetailRoute(
    innerPadding: PaddingValues,
    popBackStack: () -> Unit,
    viewModel: TripDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is TripDetailUiEvent.NavigateBack -> popBackStack()
        }
    }

    TripDetailScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}

@Composable
fun TripDetailScreen(
    innerPadding: PaddingValues,
    uiState: TripDetailUiState,
    onAction: (TripDetailUiAction) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(scrollState),
    ) {
        Text(
            text = stringResource(R.string.trip_detail_title),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(14.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            TripDetailImage("https://picsum.photos/36", uiState, onAction = onAction)
        }
    }
}

@Composable
fun TripDetailImage(
    imgUrl: String,
    uiState: TripDetailUiState,
    modifier: Modifier = Modifier,
    onAction: (TripDetailUiAction) -> Unit,
) {
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Gray004),
        ) {
            NetworkImage(
                imgUrl = imgUrl,
                modifier = Modifier.matchParentSize(),
                contentDescription = "Example Image Icon",
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 12.dp, bottom = 12.dp)
                    .clickable { },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_heart_button),
                    contentDescription = "Heart Button",
                    tint = Color.Unspecified,
                )
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = uiState.tripDetail.title,
                color = Gray001,
                style = Large20_Bold,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = uiState.tripDetail.address,
                color = Gray004,
                style = XSmall12_Reg,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = uiState.tripDetail.description,
                color = Gray001,
                style = Small14_Reg,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
        }

        TripDetailTips(uiState)

        TripDetailCategoryInfo(uiState = uiState, onAction = onAction)
    }
}

@Composable
fun TripDetailTips(uiState: TripDetailUiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Tips),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .wrapContentSize(),
                painter = painterResource(id = R.drawable.ic_tips),
                contentDescription = "tips icon",
                contentScale = ContentScale.Crop,
            )

            Text(
                text = stringResource(id = R.string.trip_tip_title),
                color = Gray001,
                style = Medium16_Mid,
            )
        }

        Text(
            modifier = Modifier.padding(start = 48.dp, end = 16.dp, bottom = 24.dp),
            text = uiState.tripDetail.tipDescription,
            color = Gray003,
            style = Small14_Reg,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TripDetailCategoryInfo(
    uiState: TripDetailUiState,
    onAction: (TripDetailUiAction) -> Unit,
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { uiState.tabs.size },
    )
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        onAction(TripDetailUiAction.OnTabChanged(pagerState.currentPage))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
            )
        }
    }
}

@Composable
private fun ContentForTab(
    tabIndex: Int,
    uiState: TripDetailUiState,
) {
    when (tabIndex) {
        0 -> DetailInfoTab(tripDetail = uiState.tripDetail)
        1 -> MateRecruitTab(tripDetail = uiState.tripDetail)
    }
}

@DevicePreview
@Composable
fun TripDetailScreenPreview() {
    TripmateTheme {
        TripDetailScreen(
            innerPadding = PaddingValues(),
            uiState = TripDetailUiState(),
            onAction = { },
        )
    }
}

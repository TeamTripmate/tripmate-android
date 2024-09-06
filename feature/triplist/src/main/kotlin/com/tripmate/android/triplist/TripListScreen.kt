package com.tripmate.android.triplist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray003
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Med
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.triplist.component.Tag
import com.tripmate.android.triplist.viewmodel.TripListUiAction
import com.tripmate.android.triplist.viewmodel.TripListUiState
import com.tripmate.android.triplist.viewmodel.TripListViewModel
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
    val scope = rememberCoroutineScope()

    // 탭 변경 시 호출되는 이벤트 처리
    LaunchedEffect(pagerState.currentPage) {
        onAction(TripListUiAction.OnTabChanged(pagerState.currentPage))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp),
    ) {
        // 상단 탭바
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

        var expanded by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            TextButton(
                onClick = { expanded = !expanded },
            ) {
                Text(
                    text = "신청한 동행",
                    color = Gray003,
                    style = Small14_Med,
                )
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null, tint = Gray003)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("신청한 동행") },
                    onClick = {
                    expanded = false
//                    onAction(TripListUiAction.OnRequestFilterChanged("신청한 동행"))
                })
                DropdownMenuItem(
                    text = { Text("작성한 동행") },
                    onClick = {
                    expanded = false
//                    onAction(TripListUiAction.OnRequestFilterChanged("작성한 동행"))
                })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            pageSpacing = 32.dp,
        ) { page ->
            when (page) {
                0 -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center // 화면의 정중앙에 배치
                    ) {
                        if (uiState.tripList.isEmpty()) {
                            Text(
                                text = "동행 기록이 없어요.",
                                style = TextStyle(color = Gray003, fontSize = 16.sp),
                                modifier = Modifier
                                    .wrapContentSize(Alignment.Center)
                            )
                        } else {
                        }
                    }
                }
                1 -> {

                }
            }
        }
    }
}

@Composable
fun ReviewComponent(
    title: String,
    date: String,
    isReviewPeriodOver: Boolean, // 상태에 따라 버튼이 달라짐
    onReviewClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp),
            )
            .background(Color.White),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Tag(
                    tagText = "1:1 동행",
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "종료된 동행",
                    style = TextStyle(fontSize = 12.sp),
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = date,
                style = TextStyle(fontSize = 14.sp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isReviewPeriodOver) {
                OutlinedButton(
                    onClick = {},
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        disabledContentColor = Color.Gray,
                    ),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text("후기 작성 기간이 끝났어요")
                }
            } else {
                OutlinedButton(
                    onClick = onReviewClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    border = BorderStroke(1.dp, Color.Blue),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text("내가 쓴 후기 보러가기", color = Color.Blue)
                }
            }
        }
    }
}


@ComponentPreview
@Composable
internal fun TripListPreview() {
    TripmateTheme {
        TripListScreen(
            innerPadding = PaddingValues(16.dp),
            uiState = TripListUiState(),
            onAction = {},
        )
    }
}

@ComponentPreview
@Composable
internal fun ReviewComponentPreview() {
    TripmateTheme {
        ReviewComponent(
            title = "서피비치에서 식사해요",
            date = "2024.08.24(일) 11:00 AM",
            isReviewPeriodOver = false,
            onReviewClick = {},
        )
    }
}

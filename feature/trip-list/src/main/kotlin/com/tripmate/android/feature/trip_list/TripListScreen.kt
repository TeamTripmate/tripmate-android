package com.tripmate.android.feature.trip_list

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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.shadow
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
import com.tripmate.android.feature.trip_list.component.Tag
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiAction
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

        // 드롭다운 메뉴 (신청한 동행, 작성한 동행)
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
//                        onAction(TripListUiAction.OnRequestFilterChanged("신청한 동행"))
                    })
                DropdownMenuItem(
                    text = { Text("작성한 동행") },
                    onClick = {
                        expanded = false
//                        onAction(TripListUiAction.OnRequestFilterChanged("작성한 동행"))
                    })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 타이틀 "동행 하루전이에요"
        Text(
            text = "동행 하루전이에요",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

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
                text = "title",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "date",
                style = TextStyle(fontSize = 14.sp),
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        // 동행 상태 바 (신청완료, 수락완료, 동행 시작, 동행 종료)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 신청 완료 점 및 텍스트
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("신청완료", style = TextStyle(color = Color.Blue, fontSize = 12.sp))
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color.Blue, shape = RoundedCornerShape(50))
                )
            }

            // 신청완료 -> 수락완료 사이의 파란 선
            Box(
                modifier = Modifier
                    .width(50.dp) // 선의 너비
                    .height(2.dp)
                    .background(Color.Blue)
                    .align(Alignment.CenterVertically) // 선을 가운데 정렬
            )

            // 수락 완료 점 및 텍스트
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("수락완료", style = TextStyle(color = Color.Blue, fontSize = 12.sp))
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color.Blue, shape = RoundedCornerShape(50))
                )
            }

            // 수락완료 -> 동행 시작 사이의 회색 선
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(2.dp)
                    .background(Color.Gray)
                    .align(Alignment.CenterVertically)
            )

            // 동행 시작 점 및 텍스트
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("동행 시작", style = TextStyle(color = Color.Gray, fontSize = 12.sp))
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(50))
                )
            }

            // 동행 시작 -> 동행 종료 사이의 회색 선
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(2.dp)
                    .background(Color.Gray)
                    .align(Alignment.CenterVertically)
            )

            // 동행 종료 점 및 텍스트
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("동행 종료", style = TextStyle(color = Color.Gray, fontSize = 12.sp))
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(50))
                )
            }
        }
        Box(
            modifier = Modifier.shadow(4.dp, RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)),
        ) {

        }


        Spacer(modifier = Modifier.height(16.dp))

        // "동행 기록이 없어요" 또는 동행 기록 리스트
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
                ReviewComponent(
                    title = "서피비치에서 식사해요",
                    date = "2024.08.24(일) 11:00 AM",
                    isReviewPeriodOver = true,
                    onReviewClick = { /* 클릭 처리 */ }
                )
            }
//            }
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
        ReviewComponent(
            title = "서피비치에서 식사해요",
            date = "2024.08.24(일) 11:00 AM",
            isReviewPeriodOver = false,
            onReviewClick = {},
        )
    }
}

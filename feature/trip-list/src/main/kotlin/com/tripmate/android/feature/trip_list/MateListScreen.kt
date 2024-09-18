package com.tripmate.android.feature.trip_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.component.NetworkImage
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Background01
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Gray009
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Primary03
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.trip_list.component.ReviewItems
import com.tripmate.android.feature.trip_list.component.TripStatusCard
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiAction
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiState
import com.tripmate.android.feature.triplist.R
import kotlinx.coroutines.launch


@Composable
fun MateListScreen(
    uiState: TripListUiState,
    innerPadding: PaddingValues,
    onAction: (TripListUiAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TripmateTopAppBar(
            navigationType = TopAppBarNavigationType.Back,
            title = "신청자 목록",
            onNavigationClick = { },
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "누구와 함께 여행가실래요?",
            style = Large20_Bold,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "동행인을 선택해주세요.",
            style = Small14_Reg,
        )
        Spacer(modifier = Modifier.height(32.dp))
        LazyColumn {
            items(3) {
                Ticket(uiState = uiState)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }


            TripmateButton(
                onClick = { },
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(start = 4.dp),
                containerColor = Primary01,
            ) {
                Text(
                    text = "선택하기",
                    color = Color.White,
                    style = Medium16_SemiBold,
                )
            }


    }
}



@Composable
fun Ticket(
    uiState: TripListUiState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 22.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // 좌측 구멍
            TicketHole(
                modifier = Modifier.align(Alignment.CenterVertically),
                backgroundColor = Gray009,
                isStart = true,
            )
            Spacer(modifier = Modifier.width(8.dp))

            // 콘텐츠 영역
            Row(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Text(
                        text = uiState.characterName,
                        style = Large20_Bold,
                        color = Gray001,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "#인생 사진 #맛집 탐방 #인스타",
                        style = XSmall12_Reg,
                        color = Gray006,
                    )
                    Spacer(modifier = Modifier.height(46.dp))
                    Text(
                        text = "캐릭터 설명보기 >",
                        style = XSmall12_Reg,
                        color = Gray006,
                    )
                }
                // 캐릭터 이미지
                NetworkImage(
                    imgUrl = uiState.characterImgUrl,
                    contentDescription = "캐릭터 이미지",
                    modifier = Modifier.size(100.dp),
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // 우측 구멍
            TicketHole(
                modifier = Modifier.align(Alignment.CenterVertically),
                backgroundColor = Gray009,
                isStart = false,
            )
        }
    }
}

@Composable
fun TicketHole(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    isStart: Boolean,
) {
    Box(
        modifier = modifier
            .size(width = 18.dp, height = 36.dp)
            .background(
                color = backgroundColor,
                shape = if (isStart)
                    RoundedCornerShape(topEnd = 36.dp, bottomEnd = 36.dp)
                else
                    RoundedCornerShape(topStart = 36.dp, bottomStart = 36.dp),
            ),
    )
}

@DevicePreview
@Composable
fun MateListScreenPreview() {
    TripmateTheme {
        MateListScreen(
            uiState = TripListUiState(),
            innerPadding = PaddingValues(16.dp),
            onAction = {},
        )
    }
}


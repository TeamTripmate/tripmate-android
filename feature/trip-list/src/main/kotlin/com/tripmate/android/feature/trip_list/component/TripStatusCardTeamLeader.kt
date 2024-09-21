package com.tripmate.android.feature.trip_list.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiAction

@OptIn(ExperimentalFoundationApi::class)
@Suppress("UnusedParameter")
@Composable
fun TripStatusCardTeamLeader(
    pagerState: PagerState,
    onAction: (TripListUiAction) -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false,
            ),
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = "동행 신청자를 확인해보세요",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                ),
                color = Gray001,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Tag(tagText = "1:1 동행")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "서피비치에서 식사해요",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "2024.08.24(일) 11:00 AM",
                    style = TextStyle(fontSize = 14.sp),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            TripmateButton(
                onClick = { onAction(TripListUiAction.OnClickViewMateList) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                enabled = true,
            ) {
                Text(
                    text = "신청자 보기",
                    style = Medium16_SemiBold,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ComponentPreview
@Composable
fun PreviewTripStatusCardTeamLeader() {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 3 },
    )
    TripmateTheme {
        TripStatusCardTeamLeader(pagerState = pagerState)
    }
}

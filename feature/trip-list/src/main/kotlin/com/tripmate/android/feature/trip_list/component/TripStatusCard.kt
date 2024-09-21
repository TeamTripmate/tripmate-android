package com.tripmate.android.feature.trip_list.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray003
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Med
import com.tripmate.android.core.designsystem.theme.TripmateTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TripStatusCard(pagerState: PagerState) {

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
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = buildAnnotatedString {
                    append("동행 하루전")
                    withStyle(style = SpanStyle(color = Gray001)) {
                        append("이에요")
                    }
                },
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                ),
                color = Primary01,
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
            TripProgressBar()
            Spacer(modifier = Modifier.height(16.dp))
//            Row(
//                Modifier
//                    .height(8.dp)
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center,
//            ) {
//                repeat(pagerState.pageCount) { iteration ->
//                    val color = if (pagerState.currentPage == iteration) Primary01 else Gray006
//                    Box(
//                        modifier = Modifier
//                            .padding(2.dp)
//                            .clip(CircleShape)
//                            .background(color)
//                            .size(8.dp),
//                    )
//                }
//            }
        }
    }
}


@Composable
fun TripProgressBar() {
    val steps = listOf("신청완료", "수락완료", "동행 시작", "동행 종료")
    val completedSteps = 2 // 예시로 2단계까지 완료된 것으로 가정

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            steps.forEachIndexed { index, step ->
                Text(
                    text = step,
                    style = TextStyle(
                        color = if (index < completedSteps) Primary01 else Gray006,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                    ),
                    textAlign = TextAlign.Center,
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            steps.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            if (index < completedSteps) Primary01 else Gray006,
                            shape = CircleShape,
                        ),
                )
                if (index < steps.size - 1) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(2.dp)
                            .background(if (index < completedSteps - 1) Primary01 else Gray006),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ComponentPreview
@Composable
fun PreviewTripStatusCard() {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 3 },
    )
    TripmateTheme {
        TripStatusCard(pagerState = pagerState)
    }
}

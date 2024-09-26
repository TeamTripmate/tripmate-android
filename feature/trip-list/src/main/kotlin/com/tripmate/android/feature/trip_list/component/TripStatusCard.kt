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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg

@OptIn(ExperimentalFoundationApi::class)
@Suppress("UnusedParameter")
@Composable
fun TripStatusCard(
    title: String,
    date: String,
    matchingStatus: String,
    selectedKeyword: List<String>,
    characterId: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Tag(tagText = "1:1 동행")
                    Text(text = "상세정보 보기>", color = Gray004, style = XSmall12_Reg)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    style = Medium16_SemiBold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = date,
                    style = XSmall12_Reg,
                    color = Gray003,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            TripProgressBar(matchingStatus)
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
fun TripProgressBar(matchingStatus: String) {
    val steps = listOf("신청완료", "수락완료", "동행 시작", "동행 종료")
    val completedSteps = when (matchingStatus) {
        "REQUEST" -> 1
        "ACCEPTED" -> 2
        "ACCOMPANY" -> 3
        "FINISHED" -> 4
        "REJECTED", "CANCELED" -> 0 // 신청 거절 또는 취소된 경우 진행 없음
        else -> 0 // 알 수 없는 상태일 경우 기본값
    }

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
    TripmateTheme {
        TripStatusCard(
            title = "서피비치에서 식사해요",
            date = "2024.08.24(일) 11:00 AM",
            matchingStatus = "ACCEPTED",
            selectedKeyword = listOf("전체"),
            characterId = "1",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
    }
}

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg

@Suppress("UnusedParameter")
@Composable
fun TripStatusCard(
    title: String,
    date: String,
    matchingStatus: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TripStatusText(matchingStatus)
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
                text = formatDateTime(date),
                style = XSmall12_Reg,
                color = Gray003,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TripProgressBar(matchingStatus)
        Spacer(modifier = Modifier.height(16.dp))
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

@Composable
fun TripStatusText(matchingStatus: String) {
    val statusText = when (matchingStatus) {
        "REQUEST" -> "동행이 신청되었어요"
        "ACCEPTED" -> "동행이 수락되었어요"
        "ACCOMPANY" -> "동행이 시작되었어요"
        "FINISHED" -> "동행이 종료되었어요"
        "REJECTED" -> "신청이 거절되었어요"
        "CANCELED" -> "동행이 취소되었어요"
        else -> "알 수 없음"
    }

    Text(
        text = buildAnnotatedString {
            val primaryText = statusText.take(6)
            val restText = statusText.drop(6)
            withStyle(style = SpanStyle(color = Primary01)) {
                append(primaryText)
            }
            withStyle(style = SpanStyle(color = Gray001)) {
                append(restText)
            }
        },
        style = Large20_Bold,
        textAlign = TextAlign.Center,
    )
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
    }
}

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray003
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.domain.entity.triplist.ApplicantInfoEntity
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiAction

@OptIn(ExperimentalFoundationApi::class)
@Suppress("UnusedParameter")
@Composable
fun TripStatusCardTeamLeader(
    title: String,
    date: String,
    companionStatus: String,
    appliedMateInfo: List<ApplicantInfoEntity>,
    modifier: Modifier = Modifier,
    onAction: (TripListUiAction) -> Unit = {},
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
            TripLeaderProgressBar(companionStatus)
            Spacer(modifier = Modifier.height(16.dp))
            if (companionStatus == "RECRUITING") {
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
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun TripLeaderProgressBar(matchingStatus: String) {
    val steps = listOf("매칭 완료", "동행 시작", "동행 종료")
    val completedSteps = when (matchingStatus) {
        "MATCHED" -> 1
        "ACCOMPANY" -> 2
        "FINISHED" -> 3
        "RECRUITING", "CANCELED" -> 0 // 모집 중이거나 취소된 경우 진행 없음
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
fun PreviewTripStatusCardTeamLeader() {
    TripmateTheme {
        TripStatusCardTeamLeader(
            title = "제목",
            date = "2021.09.01",
            companionStatus = "RECRUITING",
            appliedMateInfo = emptyList(),
        )
    }
}

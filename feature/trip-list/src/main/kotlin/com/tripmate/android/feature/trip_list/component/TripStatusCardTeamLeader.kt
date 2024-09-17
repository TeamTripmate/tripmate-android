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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray003
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Med
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.feature.triplist.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TripStatusCardTeamLeader(pagerState: PagerState) {
    // 드롭다운 메뉴 상태
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("신청한 동행") } // 기본 선택값

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
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd,
            ) {
                TextButton(
                    onClick = { expanded = !expanded },
                ) {
                    Text(
                        text = selectedOption,
                        color = Gray003,
                        style = Small14_Med,
                    )
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null, tint = Gray003)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    DropdownMenuItem(
                        text = { Text("신청한 동행") },
                        onClick = {
                            expanded = false
                            selectedOption = "신청한 동행"
                            // onAction(TripListUiAction.OnRequestFilterChanged("신청한 동행"))
                        },
                    )
                    DropdownMenuItem(
                        text = { Text("작성한 동행") },
                        onClick = {
                            expanded = false
                            selectedOption = "작성한 동행"
                            // onAction(TripListUiAction.OnRequestFilterChanged("작성한 동행"))
                        },
                    )
                }
            }
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
                onClick = { },
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
            Row(
                Modifier
                    .height(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Primary01 else Gray006
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp),
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
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 3 },
    )
    TripmateTheme {
        TripStatusCardTeamLeader(pagerState = pagerState)
    }
}

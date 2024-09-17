package com.tripmate.android.feature.trip_list.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ReviewItems(
    title: String,
    date: String,
    isReviewPeriodOver: Boolean, // 상태에 따라 버튼이 달라짐
    onReviewClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
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

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.Small14_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.feature.triplist.R

@Composable
fun ReviewItems(
    title: String,
    date: String,
    isReviewPeriodOver: Boolean, // 상태에 따라 버튼이 달라짐
    isMyCreatedTrip: Boolean,
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
                Tag(tagText = stringResource(R.string.duo_mate))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.ended_mate),
                    style = XSmall12_Reg,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = Medium16_SemiBold,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = date,
                style = Small14_Reg,
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
                    Text(
                        text = stringResource(R.string.the_review_period_has_ended),
                        style = Small14_SemiBold,
                    )
                }
            } else {
                OutlinedButton(
                    onClick = onReviewClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    border = BorderStroke(1.dp, Primary01),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = stringResource(R.string.go_see_my_review),
                        style = Small14_SemiBold,
                        color = Primary01,
                    )
                }
            }
        }
    }
}

@ComponentPreview
@Composable
fun ReviewItemsPreview() {
    TripmateTheme {
        ReviewItems(
            title = "서피비치에서 식사해요",
            date = "2024.08.24(일) 11:00 AM",
            isReviewPeriodOver = false,
            onReviewClick = { /* 클릭 처리 */ },
            isMyCreatedTrip = false,
        )
    }
}

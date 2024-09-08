package com.tripmate.android.feature.detailtrip.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.component.NetworkImage
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray003
import com.tripmate.android.core.designsystem.theme.Gray005
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Gray009
import com.tripmate.android.core.designsystem.theme.MateReview
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.Small14_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.domain.entity.TripDetailEntity
import com.tripmate.android.domain.entity.TripDetailMateReviewEntity

@Composable
fun MateReviewTab(
    tripDetail: TripDetailEntity,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        TripMateReviewAdvantage(tripDetail)

        TripMateReviewList(tripDetail)
    }
}

@Composable
fun TripMateReviewAdvantage(
    tripDetail: TripDetailEntity,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .wrapContentSize(),
            painter = painterResource(id = R.drawable.ic_introduce),
            contentDescription = "introduce icon",
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(id = R.string.trip_mate_review_advantage_title),
            color = Gray001,
            style = Medium16_Mid,
        )
    }

    val advantageTextAndColor: List<Triple<Int, Color, Color>> = listOf(
        Triple(R.string.trip_mate_review_advantage_first, Primary01, Gray001),
        Triple(R.string.trip_mate_review_advantage_second, Gray003, Gray003),
        Triple(R.string.trip_mate_review_advantage_third, Gray005, Gray005),
    )

    Spacer(modifier = Modifier.height(12.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Gray009,
                shape = RoundedCornerShape(4.dp),
            )
            .background(MateReview)
            .padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        tripDetail.tripDetailMateReviewAdvantage.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
            ) {
                Text(
                    text = stringResource(advantageTextAndColor[index].first),
                    color = advantageTextAndColor[index].second,
                    style = Medium16_Mid,
                )

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = item,
                    color = advantageTextAndColor[index].third,
                    style = Medium16_Mid,
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(24.dp))

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Gray009),
    )
}

@Composable
fun TripMateReviewList(
    tripDetail: TripDetailEntity,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .wrapContentSize(),
            painter = painterResource(id = R.drawable.ic_introduce),
            contentDescription = "introduce icon",
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(id = R.string.trip_mate_review_list),
            color = Gray001,
            style = Medium16_Mid,
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        tripDetail.tripDetailMateReviewList.forEach { item ->
            TripDetailMateReviewItem(item)
        }
    }
}

@Composable
fun TripDetailMateReviewItem(
    tripDetailMateReviewEntity: TripDetailMateReviewEntity,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = tripDetailMateReviewEntity.imageResId),
                contentDescription = "Profile Image Icon",
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = tripDetailMateReviewEntity.mateName,
                    color = Gray001,
                    style = Small14_SemiBold,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = tripDetailMateReviewEntity.mateStyleName,
                    color = Gray003,
                    style = XSmall12_Reg,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = tripDetailMateReviewEntity.mateReviewDate,
                color = Gray005,
                style = XSmall12_Reg,
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        NetworkImage(
            imgUrl = tripDetailMateReviewEntity.imageReviewUrl,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentDescription = "Example Image Icon",
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = tripDetailMateReviewEntity.mateReviewDescription,
            color = Gray006,
            style = Small14_Reg,
        )
    }
}

@ComponentPreview
@Composable
fun MateReviewTabPreview() {
    TripmateTheme {
        MateReviewTab(TripDetailEntity())
    }
}

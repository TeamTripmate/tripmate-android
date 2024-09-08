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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray002
import com.tripmate.android.core.designsystem.theme.Gray003
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Gray009
import com.tripmate.android.core.designsystem.theme.Gray010
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.Small14_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall10_Mid
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.domain.entity.TripDetailEntity
import com.tripmate.android.domain.entity.TripDetailMateRecruitEntity

@Composable
fun MateRecruitTab(
    tripDetail: TripDetailEntity,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        TripDetailMateRecruit()

        TripDetailMateRecruitList(tripDetail)
    }
}

@Composable
fun TripDetailMateRecruit() {
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
            text = stringResource(id = R.string.trip_mate_recruit_title),
            color = Gray001,
            style = Medium16_Mid,
        )
    }

    TripmateButton(
        onClick = {
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 24.dp)
            .height(56.dp),
    ) {
        Text(
            text = stringResource(R.string.trip_mate_recruit_text),
            style = Medium16_SemiBold,
        )
    }

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Gray009),
    )
}

@Composable
fun TripDetailMateRecruitList(
    tripDetail: TripDetailEntity,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .wrapContentSize(),
            painter = painterResource(id = R.drawable.ic_mate_recruit_list),
            contentDescription = "introduce icon",
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(id = R.string.trip_mate_recruit_list),
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
        tripDetail.tripDetailMateRecruit.forEach { item ->
            TripDetailMateRecruitItem(item)
        }
    }
}

@Composable
fun TripDetailMateRecruitItem(
    tripDetailMateRecruitEntity: TripDetailMateRecruitEntity,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Gray009,
                shape = RoundedCornerShape(4.dp),
            )
            .padding(20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Image(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = tripDetailMateRecruitEntity.imageResId),
                contentDescription = "Profile Image Icon",
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = tripDetailMateRecruitEntity.mateName,
                    color = Gray001,
                    style = Small14_SemiBold,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    Text(
                        text = tripDetailMateRecruitEntity.mateStyleName,
                        color = Gray003,
                        style = XSmall12_Reg,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = tripDetailMateRecruitEntity.mateMatchingRatio,
                        color = Primary01,
                        style = XSmall12_Reg,
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    items(tripDetailMateRecruitEntity.mateStyleType) { item ->
                        TripDetailMateStyleTypeItem(item)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = tripDetailMateRecruitEntity.mateRecruitTitle,
            color = Gray001,
            style = Medium16_Mid,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = tripDetailMateRecruitEntity.mateRecruitDescription,
            color = Gray006,
            style = Small14_Reg,
        )
    }
}

@Composable
fun TripDetailMateStyleTypeItem(
    mateStyleTypeItem: String,
) {
    Text(
        modifier = Modifier
            .background(color = Gray010)
            .padding(2.dp),
        text = mateStyleTypeItem,
        color = Gray002,
        style = XSmall10_Mid,
    )
}

@ComponentPreview
@Composable
fun MateRecruitTabPreview() {
    TripmateTheme {
        MateRecruitTab(TripDetailEntity())
    }
}

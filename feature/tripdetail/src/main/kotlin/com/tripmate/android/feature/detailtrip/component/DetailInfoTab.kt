package com.tripmate.android.feature.detailtrip.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
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
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray003
import com.tripmate.android.core.designsystem.theme.Gray009
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.domain.entity.TripDetailEntity
import com.tripmate.android.domain.entity.TripDetailStyleEntity
import com.tripmate.android.feature.map.MapSection
import com.tripmate.android.feature.map.state.rememberCameraPositionState

@Composable
fun DetailInfoTab(
    tripDetail: TripDetailEntity,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        TripDetailIntroduce(tripDetail)

        TripDetailLocation(tripDetail)

        TripDetailStyle(tripDetail)
    }
}

@Composable
fun TripDetailIntroduce(
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
            text = stringResource(id = R.string.trip_tip_title),
            color = Gray001,
            style = Medium16_Mid,
        )
    }

    Text(
        modifier = Modifier.padding(top = 12.dp),
        text = tripDetail.tripIntroduceDescription,
        color = Gray003,
        style = Small14_Reg,
    )

    Spacer(modifier = Modifier.height(36.dp))

    HorizontalDivider(
        thickness = 1.dp,
        color = Gray009,
        modifier = Modifier.height(1.dp),
    )
}

@Composable
fun TripDetailLocation(
    tripDetail: TripDetailEntity,
) {
    val cameraPositionState = rememberCameraPositionState()

    Spacer(modifier = Modifier.height(36.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .wrapContentSize(),
            painter = painterResource(id = R.drawable.ic_location_pin),
            contentDescription = "location pin icon",
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(id = R.string.trip_location_title),
            color = Gray001,
            style = Medium16_Mid,
        )
    }

    Spacer(modifier = Modifier.height(12.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
    ) {
        MapSection(cameraPositionState = cameraPositionState)
    }

    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = tripDetail.title,
            color = Gray001,
            style = Large20_Bold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(end = 16.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.trip_detail_phone),
                    color = Gray003,
                    style = Small14_Reg,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.trip_detail_address),
                    color = Gray003,
                    style = Small14_Reg,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.trip_detailfee),
                    color = Gray003,
                    style = Small14_Reg,
                )
            }

            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(end = 16.dp),
            ) {
                Text(
                    text = tripDetail.tripDetailPhone,
                    color = Gray003,
                    style = Small14_Reg,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = tripDetail.tripDetailAddress,
                    color = Gray003,
                    style = Small14_Reg,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = tripDetail.tripDetailFee,
                    color = Gray003,
                    style = Small14_Reg,
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(36.dp))

    HorizontalDivider(
        thickness = 1.dp,
        color = Gray009,
        modifier = Modifier.height(1.dp),
    )
}

@Composable
fun TripDetailStyle(
    tripDetail: TripDetailEntity,
) {
    Spacer(modifier = Modifier.height(36.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .wrapContentSize(),
            painter = painterResource(id = R.drawable.ic_recommend_style),
            contentDescription = "recommend style icon",
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(id = R.string.trip_recommend_style_title),
            color = Gray001,
            style = Medium16_Mid,
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(tripDetail.tripRecommendStyleEntity) { item ->
            TripDetailStyleItem(
                item,
            )
            Spacer(modifier = Modifier.width(25.dp))
        }
    }
}

@Composable
fun TripDetailStyleItem(
    tripDetailStyleEntity: TripDetailStyleEntity,
) {
    Column(
        modifier = Modifier.width(95.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.size(80.dp),
            painter = painterResource(id = tripDetailStyleEntity.imageResId),
            contentDescription = "Example Image Icon",
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = tripDetailStyleEntity.text,
            color = Gray001,
            style = Medium16_Mid,
        )
    }
}

@ComponentPreview
@Composable
fun DetailInfoTabPreview() {
    TripmateTheme {
        DetailInfoTab(TripDetailEntity())
    }
}

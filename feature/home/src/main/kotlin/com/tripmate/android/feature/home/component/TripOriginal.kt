package com.tripmate.android.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.component.NetworkImage
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray002
import com.tripmate.android.core.designsystem.theme.Gray003
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg


@Composable
fun TripOriginal(
    imgUrl: String,
    title: String,
    location: String,
    time: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable {  },
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Gray004),
        ) {
            NetworkImage(
                imgUrl = imgUrl,
                modifier = Modifier.matchParentSize(),
                contentDescription = "Example Image Icon",
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            color = Gray001,
            style = Large20_Bold,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Row {
            Text(
            text = location,
            color = Gray004,
            style = XSmall12_Reg,
        )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "·",
                color = Gray002,
                style = XSmall12_Reg,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = time,
                color = Gray004,
                style = XSmall12_Reg,
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = description,
            color = Gray003,
            style = Small14_Reg,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@ComponentPreview
@Composable
fun TripOriginalpreview() {
    TripmateTheme {
        TripOriginal(
            imgUrl = "https://picsum.photos/36",
            title = "양양 서핑 체험",
            description = "양양 서핑 체험을 통해 새로운 경험을 즐겨보세요!",
            location = "강원도 양양군",
            time = "오전 10:00 - 오후 2:00"
        )
    }
}

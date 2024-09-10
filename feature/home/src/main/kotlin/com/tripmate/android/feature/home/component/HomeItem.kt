package com.tripmate.android.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.component.NetworkImage
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg

@Composable
fun HomeItem(
    locationTag: String,
    categoryTag: String,
    mateTag: String,
    imgUrl: String,
    title: String,
    location: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column {
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

            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 14.dp),
            ) {
                Tag(
                    tagText = locationTag,
                    isLocationTag = true,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Tag(
                    tagText = categoryTag,
                    isLocationTag = false,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Tag(
                    tagText = mateTag,
                    isLocationTag = false,
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 12.dp, bottom = 12.dp)
                    .clickable { },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(com.tripmate.android.core.designsystem.R.drawable.ic_heart_button),
                    contentDescription = "Heart Button",
                    tint = Color.Unspecified,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            color = Gray001,
            style = Large20_Bold,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = location,
            color = Gray004,
            style = XSmall12_Reg,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = description,
            color = Gray001,
            style = Small14_Reg,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@ComponentPreview
@Composable
fun HomeItemPreview() {
    TripmateTheme {
        HomeItem(
            locationTag = "양양",
            categoryTag = "서핑",
            mateTag = "액티비티 동행",
            imgUrl = "https://picsum.photos/36",
            title = "양양 서핑 체험",
            description = "양양 서핑 체험을 통해 새로운 경험을 즐겨보세요!",
            location = "강원도 양양군",
        )
    }
}
package com.tripmate.android.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.component.NetworkImage
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.feature.home.R

@Composable
fun HomeItem(
    tagText1: String = "양양",
    tagText2: String = "서핑",
    tagText3: String = "액티비티 동행",
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Gray004),
        ) {
            NetworkImage(
                imgUrl = "https://picsum.photos/36",
                modifier = Modifier.matchParentSize(),
                contentDescription = "Example Image Icon",
            )

            Row(modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 14.dp),){
                Tag(
                    tagText = tagText1,
                    isLocationTag = true,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Tag(
                    tagText = tagText2,
                    isLocationTag = false,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Tag(
                    tagText = tagText3,
                    isLocationTag = false,
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 12.dp, bottom = 12.dp)
                    .clickable {  },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(com.tripmate.android.core.designsystem.R.drawable.ic_heart_button),
                    contentDescription = "Heart Button",
                    tint = Color.Unspecified
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "양양에서 서핑",
            color = Gray001,
            style = Large20_Bold,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "강원 양양군 현북면 하조대해안길 119",
            color = Gray004,
            style = XSmall12_Reg,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설설명설명설명설명명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명설명",
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
    HomeItem()
}

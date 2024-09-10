package com.tripmate.android.feature.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.component.NetworkImage
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Small14_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg

@Composable
internal fun MyPickItem(
    imgUrl: String,
    title: String,
    location: String,
    modifier: Modifier = Modifier,
) {
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Gray004),
        ) {
            NetworkImage(
                imgUrl = imgUrl,
                modifier = Modifier.matchParentSize(),
                contentDescription = "Example Image Icon",
            )
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
                    modifier = Modifier.size(32.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = location,
            color = Gray004,
            style = XSmall12_Reg,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = title,
            color = Gray001,
            style = Small14_SemiBold,
        )
    }
}

@ComponentPreview
@Composable
private fun MyPickItemPreview() {
    TripmateTheme {
        MyPickItem(
            imgUrl = "https://picsum.photos/36",
            title = "요트투어",
            location = "강를",
        )
    }
}

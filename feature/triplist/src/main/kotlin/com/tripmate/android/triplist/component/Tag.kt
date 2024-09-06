package com.tripmate.android.triplist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg

@Composable
fun Tag(
    tagText: String = "Tag",
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(2.dp))
            .background(Color(0xFFE8F1FD))
            .padding(horizontal = 4.dp, vertical = 2.dp),
    ) {
        Text(
            text = tagText,
            style = XSmall12_Reg,
            color = Primary01,
        )
    }
}

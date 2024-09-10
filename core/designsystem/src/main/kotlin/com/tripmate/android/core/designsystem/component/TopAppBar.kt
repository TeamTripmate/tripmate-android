package com.tripmate.android.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme

enum class TopAppBarNavigationType { None, Back, Close }

@Composable
fun TripmateTopAppBar(
    navigationType: TopAppBarNavigationType,
    modifier: Modifier = Modifier,
    title: String = "",
    titleStyle: TextStyle = Medium16_SemiBold,
    @DrawableRes navigationIconRes: Int = R.drawable.ic_arrow_back,
    navigationIconContentDescription: String? = null,
    containerColor: Color = Background02,
    contentColor: Color = Gray001,
    onNavigationClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(containerColor)
            .then(modifier),
    ) {
        when (navigationType) {
            TopAppBarNavigationType.None -> {
                Text(
                    text = title,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 18.dp),
                    color = contentColor,
                    style = titleStyle,
                )
            }
            TopAppBarNavigationType.Back -> {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    IconButton(
                        onClick = onNavigationClick,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .size(48.dp),
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = navigationIconRes),
                            contentDescription = navigationIconContentDescription,
                            tint = Color.Unspecified,
                        )
                    }
                    Text(
                        text = title,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(vertical = 18.dp),
                        color = contentColor,
                        style = titleStyle,
                    )
                }
            }
            TopAppBarNavigationType.Close -> {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    IconButton(
                        onClick = onNavigationClick,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(48.dp),
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_close),
                            contentDescription = navigationIconContentDescription,
                            tint = Color.Unspecified,
                        )
                    }
                    Text(
                        text = title,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(vertical = 18.dp),
                        color = contentColor,
                        style = titleStyle,
                    )
                }
            }
        }
    }
}

@ComponentPreview
@Composable
fun TripmateTopAppBarPreview() {
    TripmateTheme {
        TripmateTopAppBar(
            navigationType = TopAppBarNavigationType.None,
            title = "알림",
        )
    }
}

@ComponentPreview
@Composable
fun TripmateTopAppBarWithBackButtonPreview() {
    TripmateTheme {
        TripmateTopAppBar(
            navigationType = TopAppBarNavigationType.Back,
            title = "상세 정보",
            navigationIconContentDescription = "Navigation back icon",
        )
    }
}

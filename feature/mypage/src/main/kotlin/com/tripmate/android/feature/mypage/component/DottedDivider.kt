package com.tripmate.android.feature.mypage.component

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.TripmateTheme

@Composable
internal fun VerticalDottedDivider(
    color: Color,
    thickness: Dp,
    modifier: Modifier = Modifier,
    dash: Dp = 8.dp,
    spacedBy: Dp = 8.dp,
    cornerRadius: Dp = 2.dp,
) {
    val density = LocalDensity.current
    val length = with(density) { dash.toPx() }
    val space = with(density) { spacedBy.toPx() }
    val thicknessPx = with(density) { thickness.toPx() }
    val cornerRadiusPx = with(density) { cornerRadius.toPx() }

    Canvas(
        modifier = modifier,
        onDraw = {
            val path = Path().apply {
                val segmentHeight = length + space
                val segments = (size.height / segmentHeight).toInt()

                for (i in 0 until segments) {
                    val topY = i * segmentHeight
                    addRoundRect(
                        RoundRect(
                            left = 0f,
                            top = topY,
                            right = thicknessPx,
                            bottom = topY + length,
                            cornerRadius = CornerRadius(cornerRadiusPx),
                        ),
                    )
                }
            }

            drawPath(
                path = path,
                color = color,
            )
        },
    )
}

@ComponentPreview
@Composable
private fun VerticalDottedDividerPreview() {
    TripmateTheme {
        VerticalDottedDivider(
            color = Primary01,
            thickness = 1.dp,
        )
    }
}

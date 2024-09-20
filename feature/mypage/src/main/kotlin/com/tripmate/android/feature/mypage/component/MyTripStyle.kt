package com.tripmate.android.feature.mypage.component

import android.graphics.Picture
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.common.utils.createBitmapFromPicture
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray002
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.feature.mypage.R
import com.tripmate.android.feature.mypage.viewmodel.MyPageUiAction
import com.tripmate.android.core.designsystem.R as designSystemR

@Composable
fun MyTripStyle(
    characterName: String,
    characterImageRes: Int,
    characterTypeIntro: String,
    tripStyleIntro: String,
    isShared: Boolean,
    onAction: (MyPageUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthDp = configuration.screenWidthDp.dp
    val screenWidthPx = with(density) { screenWidthDp.toPx() }
    val heightPx = with(density) { 438.dp.toPx() }

    val gradientColors = listOf(Color(0xFF9ABCFF), Color(0xFFC4D8FF), Color(0xFFFFFFFF))
    val gradientStops = listOf(0f, 0.8f, 1f)

    val gradient = Brush.linearGradient(
        colorStops = gradientStops.zip(gradientColors).toTypedArray(),
        start = Offset(screenWidthPx / 2, 0f),
        end = Offset(screenWidthPx / 2, heightPx),
    )

    val picture = remember { Picture() }

    LaunchedEffect(isShared) {
        if (isShared) {
            val bitmap = createBitmapFromPicture(picture, gradientColors, gradientStops, heightPx)
            if (bitmap != null) {
                onAction(MyPageUiAction.OnShareMyTripStyle(bitmap))
            }
        }
    }

    Column(
        modifier = modifier
            .background(brush = gradient)
            .then(
                Modifier
                    .drawWithCache {
                        onDrawWithContent {
                            val pictureCanvas = Canvas(picture.beginRecording(size.width.toInt(), size.height.toInt()))
                            draw(
                                density = this,
                                layoutDirection = layoutDirection,
                                canvas = pictureCanvas,
                                size = size,
                            ) {
                                this@onDrawWithContent.drawContent()
                            }
                            picture.endRecording()
                            drawIntoCanvas { canvas -> canvas.nativeCanvas.drawPicture(picture) }
                        }
                    }
                    .padding(horizontal = 16.dp),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(88.dp))
        Text(
            text = stringResource(R.string.my_trip_style),
            style = Medium16_SemiBold,
            color = Gray001,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = characterName,
            style = Large20_Bold,
            textAlign = TextAlign.Center,
            color = Gray001,
        )
        Spacer(modifier = Modifier.height(22.dp))
        Image(
            painter = painterResource(id = characterImageRes),
            contentDescription = "Character Image",
            modifier = Modifier
                .height(184.dp)
                .width(212.dp),
        )
        Spacer(modifier = Modifier.height(48.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = R.string.character_type_intro),
                style = Medium16_SemiBold,
                color = Primary01,
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = characterTypeIntro,
                style = Small14_Reg,
                color = Gray002,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.trip_style_intro),
                style = Medium16_SemiBold,
                color = Primary01,
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = tripStyleIntro,
                style = Small14_Reg,
                color = Gray002,
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@ComponentPreview
@Composable
private fun MyTripStylePreview() {
    TripmateTheme {
        MyTripStyle(
            characterName = "인스타 인생 맛집\n탐험러 펭귄",
            characterImageRes = designSystemR.drawable.img_character_01,
            characterTypeIntro = "펭귄은 내향적인 성향을 가지고 있고, 대부분의 시간을 집단 내에서 조용히 보내며, 개인적인 공간과 안정적인 환경을 선호해요.\n" +
                "\n 혼자보다는 집단과 함께 있는 것을 더 편안해하고, 사회적 상호작용보다 자신의 역할에 집중합니다. 매우 세부적으로 계획을 세우고 조직적인 행동을 하는 유형이에요",
            tripStyleIntro = "펭귄은 여행을 떠나기 전에 철저한 계획을 세우는 것을 좋아해요. 여행의 주요 목적지와 일정, 활동을 미리 정해두고, 예상 가능한 상황에 대비해 준비를 철저히 할 때 안정감을 느끼며 편안하게 여행을 즐긴답니다.\n펭귄은 집단 내에서 협력하여 활동하는 것을 좋아하는데요. 여행 중에도 동행자와 함께 계획을 공유하고, 서로의 역할을 명확히 하여 협력적으로 움직이는 것을 선호하죠.",
            isShared = false,
            onAction = {},
        )
    }
}

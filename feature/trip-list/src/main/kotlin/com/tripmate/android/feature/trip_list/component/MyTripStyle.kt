package com.tripmate.android.feature.trip_list.component

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
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XLarge26_SemiBold
import com.tripmate.android.feature.triplist.R
import com.tripmate.android.core.designsystem.R as designSystemR

@Composable
fun MyTripStyle(
    characterId: String,
    characterTypeIntro: String,
    tripStyleIntro: String,
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthDp = configuration.screenWidthDp.dp
    val screenWidthPx = with(density) { screenWidthDp.toPx() }
    val heightPx = with(density) { 438.dp.toPx() }

    val gradientColors = when (characterId) {
        "PENGUIN" -> listOf(Color(0xFF9ABCFF), Color(0xFFC4D8FF), Color(0xFFFFFFFF))
        "HONEYBEE" -> listOf(Color(0xFFAEFF5D), Color(0xFFFFE7C4), Color(0xFFFFFFFF))
        "ELEPHANT" -> listOf(Color(0xFF5AC4FF), Color(0xFFFFFAF0), Color(0xFFFFFFFF))
        "DOLPHIN" -> listOf(Color(0xFF8CBAFF), Color(0xFFC0FFF0), Color(0xFFFFFFFF))
        "TURTLE" -> listOf(Color(0xFF05A5FF), Color(0xFF77C6FF), Color(0xFFFFFFFF))
        else -> listOf(Color(0xFF61E013), Color(0xFFC0FF99), Color(0xFFFFFFFF))
    }
    val gradientStops = listOf(0f, 0.8f, 1f)

    val gradient = Brush.linearGradient(
        colorStops = gradientStops.zip(gradientColors).toTypedArray(),
        start = Offset(screenWidthPx / 2, 0f),
        end = Offset(screenWidthPx / 2, heightPx),
    )

    val picture = remember { Picture() }

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
            text = "모인장의 유형은",
            style = Medium16_Mid,
            color = Gray001,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = getCharacterName(characterId),
            style = XLarge26_SemiBold,
            textAlign = TextAlign.Center,
            color = Gray001,
        )
        Spacer(modifier = Modifier.height(22.dp))
        Image(
            painter = when (characterId) {
                "PENGUIN" -> painterResource(id = designSystemR.drawable.img_character_01)
                "HONEYBEE" -> painterResource(id = designSystemR.drawable.img_character_02)
                "ELEPHANT" -> painterResource(id = designSystemR.drawable.img_character_03)
                "DOLPHIN" -> painterResource(id = designSystemR.drawable.img_character_04)
                "TURTLE" -> painterResource(id = designSystemR.drawable.img_character_05)
                else -> painterResource(id = designSystemR.drawable.img_character_06)
            },
            contentDescription = "Character Image",
            modifier = Modifier
                .height(184.dp)
                .width(212.dp),
        )
        Spacer(modifier = Modifier.height(48.dp))
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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
    Spacer(modifier = Modifier.height(56.dp))
}

private fun getCharacterName(characterId: String): String {
    return when (characterId) {
        "PENGUIN" -> "여행가 아기펭귄"
        "HONEYBEE" -> "여행가 아기꿀벌"
        "ELEPHANT" -> "여행가 아기코끼리"
        "DOLPHIN" -> "여행가 아기돌고래"
        "TURTLE" -> "여행가 아기거북이"
        else -> "여행가 아기판다"
    }
}

@ComponentPreview
@Composable
private fun MyTripStylePreview() {
    TripmateTheme {
        MyTripStyle(
            characterId = "PENGUIN",
            characterTypeIntro = "펭귄 여행가는 눈이 부릅뜬 아기 펭귄이에요. 눈이 부릅뜬 만큼 호기심이 많고, 새로운 것을 배우는 것을 좋아해요. 또한, 눈이 부릅뜬 만큼 민첩하고 빠르게 움직이는 것을 좋아해요.",
            tripStyleIntro = "펭귄 여행가는 눈이 부릅뜬 아기 펭귄이에요. 눈이 부릅뜬 만큼 호기심이 많고, 새로운 것을 배우는 것을 좋아해요. 또한, 눈이 부릅뜬 만큼 민첩하고 빠르게 움직이는 것을 좋아해요.",

        )
    }
}

package com.tripmate.android.feature.trip_list.component

import android.graphics.Picture
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.tripmate.android.core.common.utils.getCharacterImage
import com.tripmate.android.core.common.utils.getCharacterName
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray002
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XLarge24_SemiBold
import com.tripmate.android.feature.triplist.R

@Composable
fun MyTripStyle(
    isCharacterTripLead: Boolean,
    characterId: String,
    characterTypeIntro: String,
    tripStyleIntro: String,
    selectedKeywords: List<String>,
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
            text = if (isCharacterTripLead) stringResource(R.string.mate_organizer_type) else stringResource(R.string.mate_applicant_type),
            style = Medium16_Mid,
            color = Gray001,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = getCharacterName(characterId),
            style = XLarge24_SemiBold,
            textAlign = TextAlign.Center,
            color = Gray001,
        )
        Spacer(modifier = Modifier.height(22.dp))
        Image(
            painter = painterResource(id = getCharacterImage(characterId)),
            contentDescription = "Character Image",
            modifier = Modifier
                .height(184.dp)
                .width(212.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            selectedKeywords.forEach { keyword ->
                TripStyleTag(
                    tagText = keyword,
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
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
        "PENGUIN" -> "펭귄"
        "HONEYBEE" -> "꿀벌"
        "ELEPHANT" -> "코끼리"
        "DOLPHIN" -> "돌고래"
        "TURTLE" -> "거북이"
        else -> "판다"
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
            selectedKeywords = listOf("펭귄", "여행", "휴식"),
            isCharacterTripLead = false,
        )
    }
}

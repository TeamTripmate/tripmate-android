package com.tripmate.android.feature.mypage.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tripmate.android.core.common.utils.getCharacterImage
import com.tripmate.android.core.common.utils.getCharacterName
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.feature.mypage.R

@Composable
internal fun Ticket(
    characterId: String,
    type1: String,
    type2: String,
    type3: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(184.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TicketHole(
                modifier = Modifier
                    .offset(x = (-17).dp),
                backgroundColor = Background02,
                isStart = true,
            )
            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(
                    text = getCharacterName(characterId),
                    style = Large20_Bold,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    TicketType(type1)
                    Spacer(modifier = Modifier.width(4.dp))
                    TicketType(type2)
                    Spacer(modifier = Modifier.width(4.dp))
                    TicketType(type3)
                }
                Spacer(modifier = Modifier.height(46.dp))
                Row {
                    Text(
                        text = stringResource(R.string.check_character_description),
                        style = XSmall12_Reg,
                        color = Color.White,
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_right_14),
                        contentDescription = "right arrow icon",
                        tint = Color.Unspecified,
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Spacer(modifier = Modifier.height(28.dp))
                Image(
                    painter = painterResource(id = getCharacterImage(characterId)),
                    contentDescription = "character image",
                    modifier = Modifier.size(110.dp),
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            TicketHole(
                modifier = Modifier
                    .offset(x = 17.dp),
                backgroundColor = Background02,
                isStart = false,
            )
        }
    }
}

@Composable
fun TicketType(
    text: String,
    containerColor: Color = Color.Transparent,
    contentColor: Color = Color.White,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(containerColor)
            .padding(vertical = 4.dp),
    ) {
        Text(
            text = "#$text",
            style = XSmall12_Reg,
            fontSize = 10.sp,
            color = contentColor,
        )
    }
}

@Composable
fun TicketHole(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    isStart: Boolean,
) {
    Canvas(modifier = modifier.size(width = 17.dp, height = 34.dp)) {
        val path = Path().apply {
            if (isStart) {
                // 오른쪽 반원
                arcTo(
                    rect = Rect(left = 0f, top = 0f, right = size.width * 2, bottom = size.height),
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = 180f,
                    forceMoveTo = false,
                )
            } else {
                // 왼쪽 반원
                arcTo(
                    rect = Rect(left = -size.width, top = 0f, right = size.width, bottom = size.height),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 180f,
                    forceMoveTo = false,
                )
            }
        }
        drawPath(path = path, color = backgroundColor, style = Fill)
    }
}

@ComponentPreview
@Composable
private fun TickerPreview() {
    TripmateTheme {
        Ticket(
            characterId = "캐릭터 이름",
            type1 = "타입1",
            type2 = "타입2",
            type3 = "타입3",
        )
    }
}

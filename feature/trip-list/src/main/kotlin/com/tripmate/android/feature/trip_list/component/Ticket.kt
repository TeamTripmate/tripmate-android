package com.tripmate.android.feature.trip_list.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray003
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.domain.entity.TicketEntity
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiAction

@Composable
internal fun Ticket(
    ticket: TicketEntity,
    ticketIndex: Int,
    isTicketClicked: Boolean,
    onAction: (TripListUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val holeRadius = 17.dp
    val holeRadiusPx = with(density) { holeRadius.toPx() }
    val cornerRadius = 10.dp
    val cornerRadiusPx = with(density) { cornerRadius.toPx() }
    val ticketShape = TicketShape(holeRadius = holeRadiusPx, cornerRadius = cornerRadiusPx)

    // isTicketClicked 값에 따른 border 색상과 content 색상 정의
    val borderColor = if (isTicketClicked) Color(0xFF205EFF) else Color(0xFFE1E3E5)
    val backgroundColor = if (isTicketClicked) Color(0xFFE9F1FD) else Color.White
    val shadowElevation = if (isTicketClicked) 8.dp else 0.dp  // 클릭 여부에 따른 그림자

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(184.dp)
            .shadow(shadowElevation, ticketShape)
            .clip(ticketShape)
            .background(backgroundColor)
            .border(2.dp, borderColor, ticketShape)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(30.dp))
            Column {
                Text(
                    text = "펭귄",
                    style = Large20_Bold,
                    color = Gray001,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    TicketType(ticket.hashtag1)
                    Spacer(modifier = Modifier.width(4.dp))
                    TicketType(ticket.hashtag2)
                    Spacer(modifier = Modifier.width(4.dp))
                    TicketType(ticket.hashtag3)
                }
                Spacer(modifier = Modifier.height(46.dp))
                Row {
                    Text(
                        text = "캐릭터 설명보기>",
                        style = XSmall12_Reg,
                        color = Gray003,
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Spacer(modifier = Modifier.height(28.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_character_01),
                    contentDescription = "character image",
                    modifier = Modifier.size(110.dp),
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Spacer(modifier = Modifier.width(holeRadius))
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
            color = Gray003,
        )
    }
}

@ComponentPreview
@Composable
private fun TickerPreview() {
    TripmateTheme {
        Ticket(
            ticket = TicketEntity(
                ticketId = 0,
                characterName = "characterName",
                hashtag1 = "hashtag1",
                hashtag2 = "hashtag2",
                hashtag3 = "hashtag3",
                characterImgUrl = "",
            ),
            ticketIndex = 0,
            isTicketClicked = true,
            onAction = { },
        )
    }
}

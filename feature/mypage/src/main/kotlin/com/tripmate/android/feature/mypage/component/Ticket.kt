package com.tripmate.android.feature.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.component.NetworkImage
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary03
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.feature.mypage.R
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageUiState

@Composable
fun Ticket(
    uiState: MyPageUiState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(114.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(com.tripmate.android.core.designsystem.theme.Ticket),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            NetworkImage(
                imgUrl = uiState.characterImgUrl,
                contentDescription = stringResource(id = R.string.profile_image),
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(24.dp)),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center,
            ) {
                VerticalDottedDivider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.Center),
                    thickness = 2.dp,
                    color = White,
                )
                TicketHole(
                    modifier = Modifier.align(Alignment.TopCenter),
                    backgroundColor = White,
                    isTop = true,
                )
                TicketHole(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    backgroundColor = White,
                    isTop = false,
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = uiState.characterName,
                    style = Medium16_SemiBold,
                    color = Gray001,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    TicketType(uiState.type1)
                    Spacer(modifier = Modifier.width(4.dp))
                    TicketType(uiState.type2)
                    Spacer(modifier = Modifier.width(4.dp))
                    TicketType(uiState.type3)
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Composable
fun TicketType(
    text: String,
    containerColor: Color = Background02,
    contentColor: Color = Primary03,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(containerColor)
            .padding(vertical = 4.dp, horizontal = 8.dp),
    ) {
        Text(
            text = text,
            style = XSmall12_Reg,
            color = contentColor,
        )
    }
}

@Composable
fun TicketHole(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    isTop: Boolean,
) {
    Box(
        modifier = modifier
            .size(width = 18.dp, height = 9.dp)
            .background(
                color = backgroundColor,
                shape = if (isTop) RoundedCornerShape(bottomStart = 9.dp, bottomEnd = 9.dp)
                else RoundedCornerShape(topStart = 9.dp, topEnd = 9.dp),
            ),
    )
}

@ComponentPreview
@Composable
private fun TickerPreview() {
    TripmateTheme {
        Ticket(uiState = MyPageUiState())
    }
}

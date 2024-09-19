package com.tripmate.android.core.ui.component

import android.graphics.Picture
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray002
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.ui.R
import com.tripmate.android.core.designsystem.R as designSystemR

@Composable
fun MyTripStyle(
    characterName: String,
    characterImageRes: Int,
    characterTypeIntro: String,
    tripStyleIntro: String,
    modifier: Modifier = Modifier,
) {
    val picture = remember { Picture() }

    Column(
        modifier = modifier.padding(horizontal = 16.dp),
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
    }
}

@ComponentPreview
@Composable
private fun MyTripStylePreview() {
    MyTripStyle(
        characterName = "춤추는 아기판다",
        characterImageRes = designSystemR.drawable.img_character_01,
        characterTypeIntro = "춤추는 아기판다는 춤추는 것을 좋아하는 아기판다에요.",
        tripStyleIntro = "춤추는 아기판다는 춤추는 것을 좋아하는 아기판다에요.",
    )
}

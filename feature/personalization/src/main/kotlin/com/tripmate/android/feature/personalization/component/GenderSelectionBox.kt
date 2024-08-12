package com.tripmate.android.feature.personalization.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Background03
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary03
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.feature.personalization.viewmodel.Gender

@Composable
fun GenderSelectionBox(
    selectedGender: Gender,
    onSelectedChange: (Gender) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column {
            Text(
                text = stringResource(id = R.string.input_gender),
                style = Medium16_SemiBold,
                color = Gray001,
                textAlign = TextAlign.Start,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (selectedGender == Gender.FEMALE) Background03 else Background02)
                        .border(
                            width = 1.dp,
                            color = if (selectedGender == Gender.FEMALE) Primary03 else Background02,
                            shape = RoundedCornerShape(8.dp),
                        )
                        .clickable {
                            onSelectedChange(Gender.FEMALE)
                        },
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Spacer(modifier.height(16.dp))
                        Image(
                            painter = painterResource(id = R.drawable.img_female),
                            contentDescription = "female image",
                            modifier = Modifier.size(48.dp),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(id = R.string.female),
                            color = Gray001,
                            style = Medium16_Mid,
                        )
                        Spacer(modifier.height(16.dp))
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (selectedGender == Gender.MALE) Background03 else Background02)
                        .border(
                            width = 1.dp,
                            color = if (selectedGender == Gender.MALE) Primary03 else Background02,
                            shape = RoundedCornerShape(8.dp),
                        )
                        .clickable {
                            onSelectedChange(Gender.MALE)
                        },
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = painterResource(id = R.drawable.img_male),
                            contentDescription = "male image",
                            modifier = Modifier.size(48.dp),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(id = R.string.male),
                            color = Gray001,
                            style = Medium16_Mid,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@ComponentPreview
@Composable
fun GenderSelectionBoxPreview() {
    TripmateTheme {
        GenderSelectionBox(
            selectedGender = Gender.FEMALE,
            onSelectedChange = {},
        )
    }
}

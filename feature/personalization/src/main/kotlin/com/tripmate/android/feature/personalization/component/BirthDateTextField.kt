package com.tripmate.android.feature.personalization.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Large20_Reg
import com.tripmate.android.core.designsystem.theme.Large20_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme

@Composable
fun BirthRateTextField(
    birthDateText: String,
    updateBirthDateText: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column {
            Text(
                text = stringResource(id = R.string.birth_date),
                style = Large20_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(12.dp))
            BasicTextField(
                value = birthDateText,
                onValueChange = {
                    if (it.length <= 6) {
                        updateBirthDateText(it)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
                textStyle = Large20_Bold.copy(color = Gray001),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .background(
                                color = Background02,
                                shape = RoundedCornerShape(8.dp),
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier.width(20.dp))
                        Box(modifier = Modifier.weight(1f)) {
                            if (birthDateText.isEmpty()) {
                                Text(
                                    text = stringResource(id = R.string.birth_date_hint),
                                    color = Gray006,
                                    style = Large20_Reg,
                                )
                            }
                            innerTextField()
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                },
            )
        }
    }
}

@ComponentPreview
@Composable
fun BirthRateTextFieldPreview() {
    TripmateTheme {
        BirthRateTextField(
            birthDateText = "",
            updateBirthDateText = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        )
    }
}

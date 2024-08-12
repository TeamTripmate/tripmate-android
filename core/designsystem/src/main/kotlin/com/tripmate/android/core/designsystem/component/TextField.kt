package com.tripmate.android.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.theme.Error
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Gray008
import com.tripmate.android.core.designsystem.theme.Large20_Reg
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg

@Composable
fun TripmateTextField(
    text: String,
    onTextChange: (String) -> Unit,
    @StringRes searchTextHintRes: Int,
    clearText: () -> Unit,
    modifier: Modifier = Modifier,
    errorText: String? = null,
    backgroundColor: Color = Color.White,
    textColor: Color = Gray001,
    cornerShape: RoundedCornerShape = RoundedCornerShape(6.dp),
    borderStroke: BorderStroke = BorderStroke(width = 1.dp, color = if (errorText != null) Error else Gray008),
    maxLength: Int? = null,
) {
    Column(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.height(52.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            textStyle = Small14_Reg.copy(color = textColor),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .background(
                            color = backgroundColor,
                            shape = cornerShape,
                        )
                        .border(
                            border = borderStroke,
                            shape = cornerShape,
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.width(20.dp))
                    Box(modifier = Modifier.weight(1f)) {
                        if (text.isEmpty()) {
                            Text(
                                text = stringResource(id = searchTextHintRes),
                                color = Gray006,
                                style = Small14_Reg,
                            )
                        }
                        innerTextField()
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    if (text.isNotEmpty()) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_clear_text),
                            contentDescription = "clear icon",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .clickable {
                                    clearText()
                                },
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                }
            },
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            if (errorText != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = errorText,
                    color = Error,
                    style = XSmall12_Reg,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (maxLength != null) {
                Text(
                    text = "${text.length}/$maxLength",
                    color = if (errorText != null) Error else Gray008,
                    style = XSmall12_Reg,
                )
            }
        }
    }
}

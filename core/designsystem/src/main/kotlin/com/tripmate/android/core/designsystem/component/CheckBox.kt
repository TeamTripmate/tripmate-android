package com.tripmate.android.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.theme.Medium16_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme

@Composable
fun TripmateCheckBox(
    text: String,
    isSelected: Boolean,
    onSelectedChange: () -> Unit,
    iconRes: Int,
    checkedIconRes: Int,
    modifier: Modifier = Modifier,
) {
    var textLineCount by remember { mutableIntStateOf(1) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onSelectedChange()
            },
        verticalAlignment = if (textLineCount > 1) Alignment.Top else Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = if (isSelected) {
                ImageVector.vectorResource(checkedIconRes)
            } else {
                ImageVector.vectorResource(iconRes)
            },
            contentDescription = "Check Icon",
            tint = Color.Unspecified,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            style = Medium16_Reg,
            modifier = Modifier.weight(1f),
            onTextLayout = { textLayoutResult ->
                textLineCount = textLayoutResult.lineCount
            },
        )
    }
}

@ComponentPreview
@Composable
private fun TripmateCheckBoxPreview() {
    TripmateTheme {
        TripmateCheckBox(
            text = "나와 비슷한 유형의 동행 찾기",
            isSelected = false,
            onSelectedChange = {},
            iconRes = R.drawable.ic_mate_type_checked,
            checkedIconRes = R.drawable.ic_mate_type,
        )
    }
}

@ComponentPreview
@Composable
private fun TripmateMultiLineCheckBoxPreview() {
    TripmateTheme {
        TripmateCheckBox(
            text = "나와 비슷한 유형의 동행 찾기 나와 비슷한 유형의 동행 찾기 나와 비슷한 유형의 동행 찾기",
            isSelected = false,
            onSelectedChange = {},
            iconRes = R.drawable.ic_mate_type_checked,
            checkedIconRes = R.drawable.ic_mate_type,
        )
    }
}

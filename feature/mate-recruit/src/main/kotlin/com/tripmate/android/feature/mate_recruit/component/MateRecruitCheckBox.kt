package com.tripmate.android.feature.mate_recruit.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Medium16_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.feature.mate_recruit.R

@Composable
internal fun MateRecruitCheckBox(
    text: String,
    isSelected: Boolean,
    onSelectedChange: () -> Unit,
    iconRes: Int,
    checkedIconRes: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (!isSelected) {
                    onSelectedChange()
                }
            },
        verticalAlignment = Alignment.CenterVertically,
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
        )
    }
}

@ComponentPreview
@Composable
private fun MateRecruitCheckBoxPreview() {
    TripmateTheme {
        MateRecruitCheckBox(
            text = "니와 비슷한 유형의 동행 찾기",
            isSelected = false,
            onSelectedChange = {},
            iconRes = R.drawable.ic_mate_type_checked,
            checkedIconRes = R.drawable.ic_mate_type,
        )
    }
}

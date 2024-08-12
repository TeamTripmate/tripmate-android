package com.tripmate.android.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripMateDialog(
    onDismissRequest: () -> Unit,
    @StringRes titleResId: Int,
    iconResId: Int?,
    iconDescription: String?,
    @StringRes descriptionResId: Int,
    cancelTextResId: Int?,
    confirmTextResId: Int,
    onCancelClick: () -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        properties = properties,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(color = Background02),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            if (iconResId != null && iconDescription != null) {
                Icon(
                    imageVector = ImageVector.vectorResource(iconResId),
                    contentDescription = iconDescription,
                    tint = Color.Unspecified,
                )
            }
            Text(
                text = stringResource(id = titleResId),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = descriptionResId),
                color = Gray004,
                style = Small14_Reg,
            )
            Spacer(modifier = Modifier.height(48.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            ) {
                TripmateButton(
                    onClick = onConfirmClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp)
                        .then(
                            if (cancelTextResId != null) {
                                Modifier.padding(end = 4.dp)
                            } else {
                                Modifier
                            },
                        ),
                    containerColor = Primary01,
                    contentColor = Color.White,
                ) {
                    Text(
                        text = stringResource(id = confirmTextResId),
                        color = Color.White,
                        style = Medium16_SemiBold,
                    )
                }
                if (cancelTextResId != null) {
                    TripmateButton(
                        onClick = onCancelClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp)
                            .padding(start = 4.dp),
                        containerColor = Primary01,
                    ) {
                        Text(
                            text = stringResource(id = cancelTextResId),
                            color = Color.White,
                            style = Medium16_SemiBold,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@ComponentPreview
@Composable
fun TripMateDialogPreview() {
    TripmateTheme {
        TripMateDialog(
            onDismissRequest = {},
            titleResId = R.string.under_age_title,
            iconResId = null,
            iconDescription = "",
            descriptionResId = R.string.under_age_description,
            cancelTextResId = null,
            confirmTextResId = R.string.under_age_confirm,
            onCancelClick = {},
            onConfirmClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}

package com.tripmate.trip_original.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.tripmate.trip_original.viewmodel.TripOriginalUiAction
import com.tripmate.android.core.common.extension.noRippleClickable
import com.tripmate.android.core.designsystem.ComponentPreview

import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Small14_Med
import com.tripmate.android.core.designsystem.theme.Small14_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.R as designSystemR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TripOriginalDialog(
    onDismissRequest: () -> Unit,
    onAction: (TripOriginalUiAction) -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        properties = properties,
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Background02),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = stringResource(designSystemR.string.trip_original_title),
                    modifier = Modifier.padding(horizontal = 42.dp),
                    color = Gray001,
                    textAlign = TextAlign.Center,
                    style = Medium16_SemiBold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(designSystemR.string.trip_original_desc),
                    color = Gray004,
                    textAlign = TextAlign.Center,
                    style = Small14_Med,
                )
                Spacer(modifier = Modifier.height(50.dp))
                TripmateButton(
                    onClick = {
                        onAction(TripOriginalUiAction.OnMateClosed)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .padding(horizontal = 24.dp),
                ) {
                    Text(
                        text = stringResource(designSystemR.string.trip_original_confirm),
                        style = Small14_SemiBold,
                        color = Color.White,
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
            Icon(
                imageVector = ImageVector.vectorResource(designSystemR.drawable.ic_close),
                contentDescription = "withdraw",
                tint = Color.Unspecified,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .noRippleClickable { onAction(TripOriginalUiAction.OnMateClosed) },
            )
        }
    }
}

@ComponentPreview
@Composable
private fun TripOriginalDialogPreview() {
    TripmateTheme {
        TripOriginalDialog(
            onAction = {},
            onDismissRequest = {},
        )
    }
}

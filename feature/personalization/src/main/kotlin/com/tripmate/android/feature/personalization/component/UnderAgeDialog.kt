package com.tripmate.android.feature.personalization.component

import androidx.compose.runtime.Composable
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.component.TripmateDialog
import com.tripmate.android.core.designsystem.theme.TripmateTheme

@Composable
fun UnderAgeDialog(
    onConfirmClick: () -> Unit,
) {
    TripmateDialog(
        onDismissRequest = { },
        titleResId = R.string.under_age_title,
        iconResId = null,
        iconDescription = "",
        descriptionResId = R.string.under_age_description,
        cancelTextResId = null,
        confirmTextResId = R.string.under_age_confirm,
        onCancelClick = {},
        onConfirmClick = onConfirmClick,
    )
}

@ComponentPreview
@Composable
fun UnderAgeDialogPreview() {
    TripmateTheme {
        UnderAgeDialog(
            onConfirmClick = { },
        )
    }
}

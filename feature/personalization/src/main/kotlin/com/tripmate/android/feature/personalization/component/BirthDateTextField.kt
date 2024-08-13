package com.tripmate.android.feature.personalization.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.component.TripmateTextField
import com.tripmate.android.core.designsystem.theme.TripmateTheme

@Composable
fun BirthRateTextField(
    birthDateText: String,
    updateBirthDateText: (String) -> Unit,
    clearText: () -> Unit,
    modifier: Modifier = Modifier,
    errorText: String? = null,
) {
    TripmateTextField(
        text = birthDateText,
        onTextChange = updateBirthDateText,
        searchTextHintRes = R.string.birth_date_hint,
        clearText = clearText,
        modifier = modifier,
        errorText = errorText,
        maxLength = null,
    )
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
                .height(52.dp),
            clearText = {},
        )
    }
}

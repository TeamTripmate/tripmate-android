package com.tripmate.android.core.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Gray009
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.TripmateTheme

@Composable
fun TripmateButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = Primary01,
    contentColor: Color = Color.White,
    disabledContainerColor: Color = Gray009,
    disabledContentColor: Color = Gray004,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        ),
        contentPadding = contentPadding,
        content = content,
    )
}

@ComponentPreview
@Composable
fun TripmateButtonPreview() {
    TripmateTheme {
        TripmateButton(
            onClick = {},
        ) {
            Text("Button")
        }
    }
}

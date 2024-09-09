package com.tripmate.android.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    TripmateButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        contentPadding = if (leadingIcon != null) {
            ButtonDefaults.ButtonWithIconContentPadding
        } else {
            ButtonDefaults.ContentPadding
        },
    ) {
        TrimpateButtonContent(
            text = text,
            leadingIcon = leadingIcon,
        )
    }
}

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

@Composable
fun TripmateOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    border: BorderStroke? = BorderStroke(
        width = 1.dp,
        color = if (enabled) Primary01 else Gray004
    ),
    containerColor: Color = Color.White,
    contentColor: Color = Primary01,
    disabledContainerColor: Color = Color.White,
    disabledContentColor: Color = Gray004,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        border = border,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        ),
        contentPadding = contentPadding,
        content = content,
    )
}

@Composable
private fun TrimpateButtonContent(
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    if (leadingIcon != null) {
        Box(Modifier.sizeIn(maxHeight = ButtonDefaults.IconSize)) {
            leadingIcon()
        }
    }
    Box(
        Modifier.padding(
            start = if (leadingIcon != null) {
                ButtonDefaults.IconSpacing
            } else {
                0.dp
            },
        ),
    ) {
        text()
    }
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

@ComponentPreview
@Composable
fun TripmateButtonDisabledPreview() {
    TripmateTheme {
        TripmateButton(
            onClick = {},
            enabled = false,
        ) {
            Text("Button")
        }
    }
}

@ComponentPreview
@Composable
fun TripmateButtonWithLeadingIconPreview() {
    TripmateTheme {
        TripmateButton(
            onClick = {},
            text = {
                Text("Button")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Check icon",
                    tint = Color.White,
                )
            },
        )
    }
}

@ComponentPreview
@Composable
fun TripmateOutlinedButtonPreview() {
    TripmateTheme {
        TripmateOutlinedButton(
            onClick = {},
        ) {
            Text("Button")
        }
    }
}

@ComponentPreview
@Composable
fun TripmateOutlinedButtonDisabledPreview() {
    TripmateTheme {
        TripmateOutlinedButton(
            onClick = {},
            enabled = false,
        ) {
            Text("Button")
        }
    }
}

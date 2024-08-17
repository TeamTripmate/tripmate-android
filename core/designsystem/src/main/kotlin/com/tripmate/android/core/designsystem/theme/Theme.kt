package com.tripmate.android.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Primary01Dark,
    secondary = Primary02Dark,
    tertiary = Primary03Dark,
    background = Background01Dark,
    surface = Background02Dark,
    onPrimary = Gray009Dark,
    onSecondary = Gray009Dark,
    onTertiary = Gray009Dark,
    onBackground = Gray001Dark,
    onSurface = Gray001Dark,
    onSurfaceVariant = Gray005Dark,
)

private val LightColorScheme = lightColorScheme(
    primary = Primary01,
    secondary = Primary02,
    tertiary = Primary03,
    background = Background01,
    surface = Background02,
    onPrimary = Gray009,
    onSecondary = Gray009,
    onTertiary = Gray009,
    onBackground = Gray001,
    onSurface = Gray001,
    onSurfaceVariant = Gray005,
)

@Composable
fun TripmateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}

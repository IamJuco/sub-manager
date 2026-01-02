package com.juco.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalSubManagerColors = staticCompositionLocalOf<SubManagerColors> {
    error("No SubManagerColors provided")
}
private val LocalSubManagerTypography = staticCompositionLocalOf<SubManagerTypography> {
    error("No SubManagerTypography provided")
}

object SubManagerTheme {
    val colors: SubManagerColors
        @Composable
        @ReadOnlyComposable
        get() = LocalSubManagerColors.current
    val typography: SubManagerTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalSubManagerTypography.current
}

@Composable
fun ProvideSubManagerColors(
    colors: SubManagerColors,
    typography: SubManagerTypography,
    content: @Composable () -> Unit
) {
    val provideColors = remember { colors.copy() }.apply { update(colors) }
    CompositionLocalProvider(
        LocalSubManagerColors provides provideColors,
        LocalSubManagerTypography provides typography,
        content = content
    )
}

@Composable
fun SubManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val typography = typography()
    val colors = if (darkTheme) {
        SubManagerDarkColors()
    } else {
        SubManagerLightColors()
    }

    ProvideSubManagerColors(
        colors = colors,
        typography = typography
    ) {
        MaterialTheme(
            content = content
        )
    }
}
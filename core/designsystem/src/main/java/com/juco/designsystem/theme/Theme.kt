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

object SubManagerTheme {
    val colors: SubManagerColors
    @Composable
    @ReadOnlyComposable
    get() = LocalSubManagerColors.current
}

@Composable
fun ProvideSubManagerColors(
    colors: SubManagerColors,
    content: @Composable () -> Unit
) {
    val provideColors = remember { colors.copy() }.apply { update(colors) }
    CompositionLocalProvider(
        LocalSubManagerColors provides provideColors,
        content = content
    )
}

@Composable
fun SubManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        SubManagerDarkColors()
    } else {
        SubManagerLightColors()
    }

    ProvideSubManagerColors(
        colors = colors
    ) {
        MaterialTheme(
            content = content
        )
    }
}
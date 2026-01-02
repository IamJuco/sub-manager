package com.juco.designsystem.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

val PrimaryBackgroundLight = Color(0xFFF9FAFB)
val PrimaryBackgroundDark = Color(0xFF121212)

val SecondaryBackgroundLight = Color(0xFFFFFFFF)
val SecondaryBackgroundDark = Color(0xFF1E1E1E)

val PrimaryTextLight = Color(0XFF1F2937)
val PrimaryTextDark = Color(0xFFF3F4F6)

val SecondaryTextLight = Color(0xFF6B7280)
val SecondaryTextDark = Color(0xFF9CA3AF)

val DividerLight = Color(0xFFE5E7EB)
val DividerDark = Color(0xFF374151)

val ButtonLight = Color(0xFF374151)
val ButtonDark = Color(0xFFE5E7EB)

@Stable
class SubManagerColors(
    primaryBackground: Color,
    secondaryBackground: Color,
    primaryText: Color,
    secondaryText: Color,
    divider: Color,
    button: Color
) {
    var primaryBackground by mutableStateOf(primaryBackground)
        private set
    var secondaryBackground by mutableStateOf(secondaryBackground)
        private set
    var primaryText by mutableStateOf(primaryText)
        private set
    var secondaryText by mutableStateOf(secondaryText)
        private set
    var divider by mutableStateOf(divider)
        private set
    var button by mutableStateOf(button)
        private set

    fun copy(): SubManagerColors = SubManagerColors(
        primaryBackground,
        secondaryBackground,
        primaryText,
        secondaryText,
        divider,
        button
    )

    fun update(other: SubManagerColors) {
        primaryBackground = other.primaryBackground
        secondaryBackground = other.secondaryBackground
        primaryText = other.primaryText
        secondaryText = other.secondaryText
        divider = other.divider
        button = other.button
    }
}

fun SubManagerLightColors(
    PrimaryBackground: Color = PrimaryBackgroundLight,
    SecondaryBackground: Color = SecondaryBackgroundLight,
    PrimaryText: Color = PrimaryTextLight,
    SecondaryText: Color = SecondaryTextLight,
    Divider: Color = DividerLight,
    Button: Color = ButtonLight
) = SubManagerColors(
    PrimaryBackground,
    SecondaryBackground,
    PrimaryText,
    SecondaryText,
    Divider,
    Button
)

fun SubManagerDarkColors(
    PrimaryBackground: Color = PrimaryBackgroundDark,
    SecondaryBackground: Color = SecondaryBackgroundDark,
    PrimaryText: Color = PrimaryTextDark,
    SecondaryText: Color = SecondaryTextDark,
    Divider: Color = DividerDark,
    Button: Color = ButtonDark
) = SubManagerColors(
    PrimaryBackground,
    SecondaryBackground,
    PrimaryText,
    SecondaryText,
    Divider,
    Button
)
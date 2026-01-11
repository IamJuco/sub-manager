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

val DisabledBackgroundLight = Color(0xFFE5E7EB)
val DisabledBackgroundDark = Color(0xFF2C2C2E)

val DisabledTextLight = Color(0xFF9CA3AF)
val DisabledTextDark = Color(0xFF6B7280)

val OutlineLight = Color(0xFFD1D5DB)
val OutlineDark = Color(0xFF4B5563)

val RedLight = Color(0xFFFF3B30)
val RedDark = Color(0xFFFF453A)

@Stable
class SubManagerColors(
    primaryBackground: Color,
    secondaryBackground: Color,
    primaryText: Color,
    secondaryText: Color,
    divider: Color,
    button: Color,
    disabledBackground: Color,
    disabledText: Color,
    outline: Color,
    red: Color
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
    var disabledBackground by mutableStateOf(disabledBackground)
        private set
    var disabledText by mutableStateOf(disabledText)
        private set
    var outline by mutableStateOf(outline)
        private set
    var red by mutableStateOf(red)
        private set

    fun copy(
        primaryBackground: Color = this.primaryBackground,
        secondaryBackground: Color = this.secondaryBackground,
        primaryText: Color = this.primaryText,
        secondaryText: Color = this.secondaryText,
        divider: Color = this.divider,
        button: Color = this.button,
        disabledBackground: Color = this.disabledBackground,
        disabledText: Color = this.disabledText,
        outline: Color = this.outline
    ): SubManagerColors = SubManagerColors(
        primaryBackground,
        secondaryBackground,
        primaryText,
        secondaryText,
        divider,
        button,
        disabledBackground,
        disabledText,
        outline,
        red
    )

    fun update(other: SubManagerColors) {
        primaryBackground = other.primaryBackground
        secondaryBackground = other.secondaryBackground
        primaryText = other.primaryText
        secondaryText = other.secondaryText
        divider = other.divider
        button = other.button
        disabledBackground = other.disabledBackground
        disabledText = other.disabledText
        outline = other.outline
        red = other.red
    }
}

fun SubManagerLightColors(
    PrimaryBackground: Color = PrimaryBackgroundLight,
    SecondaryBackground: Color = SecondaryBackgroundLight,
    PrimaryText: Color = PrimaryTextLight,
    SecondaryText: Color = SecondaryTextLight,
    Divider: Color = DividerLight,
    Button: Color = ButtonLight,
    DisabledBackground: Color = DisabledBackgroundLight,
    DisabledText: Color = DisabledTextLight,
    Outline: Color = OutlineLight,
    red: Color = RedLight
) = SubManagerColors(
    PrimaryBackground,
    SecondaryBackground,
    PrimaryText,
    SecondaryText,
    Divider,
    Button,
    DisabledBackground,
    DisabledText,
    Outline,
    red
)

fun SubManagerDarkColors(
    PrimaryBackground: Color = PrimaryBackgroundDark,
    SecondaryBackground: Color = SecondaryBackgroundDark,
    PrimaryText: Color = PrimaryTextDark,
    SecondaryText: Color = SecondaryTextDark,
    Divider: Color = DividerDark,
    Button: Color = ButtonDark,
    DisabledBackground: Color = DisabledBackgroundDark,
    DisabledText: Color = DisabledTextDark,
    Outline: Color = OutlineDark,
    red: Color = RedDark
) = SubManagerColors(
    PrimaryBackground,
    SecondaryBackground,
    PrimaryText,
    SecondaryText,
    Divider,
    Button,
    DisabledBackground,
    DisabledText,
    Outline,
    red
)
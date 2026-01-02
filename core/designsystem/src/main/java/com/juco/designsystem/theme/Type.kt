package com.juco.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.juco.submanager.designsystem.R

private val pretendardBold = FontFamily(Font(R.font.pretendard_bold, FontWeight.Bold))
private val pretendardSemiBold = FontFamily(Font(R.font.pretendard_semibold, FontWeight.SemiBold))
private val pretendardRegular = FontFamily(Font(R.font.pretendard_regular, FontWeight.Medium))

private fun boldTextStyle(
    fontSize: TextUnit,
    fontFamily: FontFamily = pretendardBold,
    lineHeight: TextUnit = 1.35.em,
    letterSpacing: TextUnit = (-0.012).em
): TextStyle = TextStyle(
    fontSize = fontSize,
    fontFamily = fontFamily,
    lineHeight = lineHeight,
    letterSpacing = letterSpacing,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

private fun semiBoldTextStyle(
    fontSize: TextUnit,
    fontFamily: FontFamily = pretendardSemiBold,
    lineHeight: TextUnit,
    letterSpacing: TextUnit
): TextStyle = TextStyle(
    fontSize = fontSize,
    fontFamily = fontFamily,
    lineHeight = lineHeight,
    letterSpacing = letterSpacing,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

private fun regularTextStyle(
    fontSize: TextUnit,
    fontFamily: FontFamily = pretendardRegular,
    lineHeight: TextUnit,
    letterSpacing: TextUnit
): TextStyle = TextStyle(
    fontSize = fontSize,
    fontFamily = fontFamily,
    lineHeight = lineHeight,
    letterSpacing = letterSpacing,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

@Immutable
data class SubManagerTypography(
    // Display & Headlines
    val h1Bold: TextStyle,
    val h2SemiBold: TextStyle,
    val h3SemiBold: TextStyle,

    // Body
    val b1Regular: TextStyle,
    val b1SemiBold: TextStyle,
    val b2Regular: TextStyle,
    val b2SemiBold: TextStyle,

    // Caption & Labels
    val c1Regular: TextStyle,
    val c1SemiBold: TextStyle,
    val c2Regular: TextStyle
)

fun typography() = SubManagerTypography(
    h1Bold = boldTextStyle(
        fontSize = 32.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.02).em
    ),
    h2SemiBold = semiBoldTextStyle(
        fontSize = 24.sp,
        lineHeight = 1.35.em,
        letterSpacing = (-0.015).em
    ),
    h3SemiBold = semiBoldTextStyle(
        fontSize = 20.sp,
        lineHeight = 1.4.em,
        letterSpacing = (-0.01).em
    ),

    b1Regular = regularTextStyle(
        fontSize = 16.sp,
        lineHeight = 1.5.em,
        letterSpacing = (-0.004).em
    ),
    b1SemiBold = semiBoldTextStyle(
        fontSize = 16.sp,
        lineHeight = 1.5.em,
        letterSpacing = (-0.004).em
    ),
    b2Regular = regularTextStyle(
        fontSize = 14.sp,
        lineHeight = 1.45.em,
        letterSpacing = (-0.004).em
    ),
    b2SemiBold = semiBoldTextStyle(
        fontSize = 14.sp,
        lineHeight = 1.45.em,
        letterSpacing = (-0.004).em
    ),

    c1Regular = regularTextStyle(
        fontSize = 12.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.em
    ),
    c1SemiBold = semiBoldTextStyle(
        fontSize = 12.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.em
    ),
    c2Regular = regularTextStyle(
        fontSize = 10.sp,
        lineHeight = 1.2.em,
        letterSpacing = 0.01.em
    )
)
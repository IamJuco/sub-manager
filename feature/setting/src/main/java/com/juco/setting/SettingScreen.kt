package com.juco.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun SettingRoute(
    padding: PaddingValues
) {
    SettingScreen(
        padding = padding
    )
}

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SubManagerTheme.colors.primaryBackground)
            .padding(padding)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Setting 화면"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingScreenPreview() {
    SubManagerTheme {
        SettingScreen(
            padding = PaddingValues()
        )
    }
}
package com.juco.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.component.SubManagerMenuTopBar
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.setting.component.VersionSection

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
        SubManagerMenuTopBar(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp),
            title = "세팅 화면"
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = SubManagerTheme.colors.primaryText,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        VersionSection(
            onClick = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = SubManagerTheme.colors.primaryText,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(16.dp))
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
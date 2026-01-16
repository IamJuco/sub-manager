package com.juco.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.component.SubManagerMenuTopBar
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.setting.component.VersionSection

@Composable
fun SettingRoute(
    padding: PaddingValues,
    appVersion: String,
    isAppUpdateAvailable: Boolean,
    onUpdateClick: () -> Unit
) {
    SettingScreen(
        padding = padding,
        appVersion = appVersion,
        isAppUpdateAvailable = isAppUpdateAvailable,
        onUpdateClick = onUpdateClick
    )
}

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    appVersion: String,
    isAppUpdateAvailable: Boolean,
    onUpdateClick: () -> Unit
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
            title = "설정"
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = SubManagerTheme.colors.primaryText,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        VersionSection(
            appVersion = appVersion,
            isAppUpdateAvailable = isAppUpdateAvailable,
            onUpdateClick = onUpdateClick
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
            padding = PaddingValues(),
            appVersion = "",
            isAppUpdateAvailable = false,
            onUpdateClick = {}
        )
    }
}
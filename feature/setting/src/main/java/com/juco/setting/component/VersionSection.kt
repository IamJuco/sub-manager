package com.juco.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun VersionSection(
    modifier: Modifier = Modifier,
    appVersion: String,
    isAppUpdateAvailable: Boolean,
    onUpdateClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth().clickable {
            onUpdateClick()
        }
    ) {
        Text(
            text = "버전 $appVersion",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText,
        )
        if (isAppUpdateAvailable) {
            Text(
                text = "버전 업데이트가 필요합니다.",
                style = SubManagerTheme.typography.c1Regular,
                color = SubManagerTheme.colors.secondaryText
            )
        } else {
            Text(
                text = "최신 버전을 사용 중입니다.",
                style = SubManagerTheme.typography.c1Regular,
                color = SubManagerTheme.colors.secondaryText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VersionSectionPreview() {
    SubManagerTheme {
        VersionSection(
            appVersion = "",
            isAppUpdateAvailable = false,
            onUpdateClick = {}
        )
    }
}
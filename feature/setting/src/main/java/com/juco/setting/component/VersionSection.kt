package com.juco.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun VersionSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Text(
            text = "버전 1.0.0",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText,
        )
        Text(
            text = "버전을 업데이트해주세요.",
            style = SubManagerTheme.typography.c1Regular,
            color = SubManagerTheme.colors.secondaryText
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VersionSectionPreview() {
    SubManagerTheme {
        VersionSection(
            onClick = {}
        )
    }
}
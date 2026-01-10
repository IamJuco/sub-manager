package com.juco.subscription_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.component.button.SubManagerButton
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun SettingSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "서비스 수정 또는 삭제",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SubManagerButton(
                modifier = Modifier.weight(1f),
                text = "수정 하기",
                onClick = {
                }
            )
            SubManagerButton(
                modifier = Modifier.weight(1f),
                text = "삭제 하기",
                onClick = {
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingSectionPreview() {
    SubManagerTheme {
        SettingSection()
    }
}
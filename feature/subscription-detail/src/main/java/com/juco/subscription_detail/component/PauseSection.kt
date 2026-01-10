package com.juco.subscription_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun PauseSection(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "일시 정지",
                        style = SubManagerTheme.typography.h3SemiBold,
                        color = SubManagerTheme.colors.primaryText,
                    )
                    Text(
                        text = "이 서비스를 일시정지 합니다",
                        style = SubManagerTheme.typography.c1Regular,
                        color = SubManagerTheme.colors.secondaryText
                    )
                    Text(
                        text = "일시 정지를 하면 이 서비스에 대한 모든 계산 및 알람 설정을 멈춥니다",
                        style = SubManagerTheme.typography.c1Regular,
                        color = SubManagerTheme.colors.secondaryText
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Switch(
                    checked = isChecked,
                    onCheckedChange = { shouldCheck ->
                        if (shouldCheck) {
                            onCheckedChange(true)
                        } else {
                            onCheckedChange(false)
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = SubManagerTheme.colors.primaryBackground,
                        checkedTrackColor = SubManagerTheme.colors.primaryText,
                        uncheckedThumbColor = SubManagerTheme.colors.button,
                        uncheckedTrackColor = SubManagerTheme.colors.secondaryBackground,
                        uncheckedBorderColor = SubManagerTheme.colors.disabledText
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingSectionPreview() {
    SubManagerTheme {
        PauseSection(
            isChecked = false,
            onCheckedChange = {}
        )
    }
}
package com.juco.subscription_add.add.component

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun NotificationSection(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var showPermissionDialog by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                onCheckedChange(true)
            } else {
                showPermissionDialog = true
                onCheckedChange(false)
            }
        }
    )

    if (showPermissionDialog) {
        NotificationDialog(
            onDismissRequest = { showPermissionDialog = false }
        )
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "결제일 미리 알람 설정",
                style = SubManagerTheme.typography.h3SemiBold,
                color = SubManagerTheme.colors.primaryText
            )
            Text(
                text = "이 서비스에 대한 알람을 설정합니다",
                style = SubManagerTheme.typography.c1Regular,
                color = SubManagerTheme.colors.secondaryText
            )
            Text(
                text = "결제 하루 전, 잊지 않고 챙길 수 있게 알람을 보내드릴게요",
                style = SubManagerTheme.typography.c1Regular,
                color = SubManagerTheme.colors.secondaryText
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Switch(
            checked = isChecked,
            onCheckedChange = { shouldCheck ->
                if (shouldCheck) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        val hasPermission = ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) == PackageManager.PERMISSION_GRANTED

                        if (hasPermission) {
                            onCheckedChange(true)
                        } else {
                            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS) // 권한 요청
                        }
                    } else {
                        onCheckedChange(true)
                    }
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

@Preview(showBackground = true)
@Composable
private fun NotificationSectionPreview() {
    SubManagerTheme {
        NotificationSection(
            isChecked = false,
            onCheckedChange = {}
        )
    }
}
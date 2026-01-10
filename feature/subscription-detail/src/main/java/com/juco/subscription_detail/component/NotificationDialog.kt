package com.juco.subscription_detail.component

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun NotificationDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    val context = LocalContext.current

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        containerColor = SubManagerTheme.colors.secondaryBackground,
        title = {
            Text(
                text = "알림 권한 필요",
                style = SubManagerTheme.typography.h3SemiBold,
                color = SubManagerTheme.colors.primaryText
            )
        },
        text = {
            Text(
                text = "결제일 알림을 받으려면 설정에서 알림 권한을 허용해야 합니다.",
                style = SubManagerTheme.typography.b1Regular,
                color = SubManagerTheme.colors.primaryText
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                    val intent = Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", context.packageName, null)
                    )
                    context.startActivity(intent)
                }
            ) {
                Text(
                    text = "설정으로 이동",
                    style = SubManagerTheme.typography.b1SemiBold,
                    color = SubManagerTheme.colors.primaryText
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(
                    text = "취소",
                    style = SubManagerTheme.typography.b1Regular,
                    color = SubManagerTheme.colors.secondaryText
                )
            }
        }
    )
}

@Preview
@Composable
private fun NotificationDialogPreview() {
    SubManagerTheme {
        NotificationDialog(
            onDismissRequest = {}
        )
    }
}
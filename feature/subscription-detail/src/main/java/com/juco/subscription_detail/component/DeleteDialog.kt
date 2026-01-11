package com.juco.subscription_detail.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun DeleteDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        containerColor = SubManagerTheme.colors.secondaryBackground,
        titleContentColor = SubManagerTheme.colors.primaryText,
        textContentColor = SubManagerTheme.colors.secondaryText,
        title = {
            Text(
                text = "구독 삭제",
                style = SubManagerTheme.typography.h3SemiBold
            )
        },
        text = {
            Text(
                text = "정말 이 구독 정보를 삭제하시겠습니까?\n삭제된 데이터는 복구할 수 없습니다.",
                style = SubManagerTheme.typography.b1Regular
            )
        },
        confirmButton = {
            androidx.compose.material3.TextButton(
                onClick = onConfirm
            ) {
                Text(
                    text = "삭제",
                    style = SubManagerTheme.typography.b1SemiBold,
                    color = androidx.compose.ui.graphics.Color.Red
                )
            }
        },
        dismissButton = {
            androidx.compose.material3.TextButton(
                onClick = onDismissRequest
            ) {
                Text(
                    text = "취소",
                    style = SubManagerTheme.typography.b1Regular,
                    color = SubManagerTheme.colors.secondaryText
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun DeleteDialogPreview() {
    SubManagerTheme {
        DeleteDialog(
            onDismissRequest = {},
            onConfirm = {}
        )
    }
}
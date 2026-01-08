package com.juco.designsystem.component.button

import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.juco.designsystem.theme.SubManagerTheme
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.Color

@Composable
fun SubManagerFloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    containerColor: Color = SubManagerTheme.colors.primaryText,
    contentColor: Color = SubManagerTheme.colors.primaryBackground
) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        containerColor = containerColor,
        contentColor = contentColor,
        icon = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "구독 추가 버튼"
            )
        },
        text = {
            Text(
                text = "구독 추가",
                style = SubManagerTheme.typography.b1SemiBold
            )
        },
    )
}

@Preview
@Composable
private fun SubManagerFloatingButtonPreview() {
    SubManagerTheme {
        SubManagerFloatingButton(
            onClick =  {}
        )
    }
}
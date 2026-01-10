package com.juco.designsystem.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun SubManagerButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    text: String,
    textStyle: TextStyle = SubManagerTheme.typography.c1SemiBold,
    enabledTextColor: Color = SubManagerTheme.colors.primaryBackground,
    disabledTextColor: Color = SubManagerTheme.colors.primaryText,
    enabledBackgroundColor: Color = SubManagerTheme.colors.primaryText,
    disabledBackgroundColor: Color = SubManagerTheme.colors.primaryText

) {
    val backgroundColor = if (enabled) enabledBackgroundColor else disabledBackgroundColor
    val contentColor = if (enabled) enabledTextColor else disabledTextColor

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                style = textStyle,
                color = contentColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SubManagerButtonPreview() {
    SubManagerTheme {
        Column {
            SubManagerButton(
                modifier = Modifier.fillMaxWidth(),
                text = "완료",
                onClick = {},
                enabled = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SubManagerButtonPreview2() {
    SubManagerTheme {
        Column {
            SubManagerButton(
                modifier = Modifier.fillMaxWidth(),
                text = "완료",
                onClick = {},
                enabled = false
            )
        }
    }
}
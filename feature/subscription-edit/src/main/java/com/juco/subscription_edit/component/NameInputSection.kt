package com.juco.subscription_edit.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.component.textfield.SubManagerTextField
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun NameInputSection(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "서비스 이름 수정",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText
        )

        Spacer(Modifier.height(4.dp))

        SubManagerTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= 10) {
                    onValueChange(newValue)
                }
            },
            placeholder = "서비스 이름"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NameInputSectionPreview() {
    SubManagerTheme {
        NameInputSection(
            value = "",
            onValueChange = {}
        )
    }
}
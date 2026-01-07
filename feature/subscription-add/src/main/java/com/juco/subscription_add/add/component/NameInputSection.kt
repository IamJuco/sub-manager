package com.juco.subscription_add.add.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.component.textfield.SubManagerTextField
import com.juco.designsystem.theme.SubManagerTheme
import kotlin.String

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
            text = "구독 중인 서비스 이름을",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText
        )

        Text(
            text = "입력해주세요",
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
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            NameInputSection(
                value = "",
                onValueChange = {}
            )
        }
    }
}
package com.juco.subscription_edit.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.common.visualtransformation.CommaVisualTransformation
import com.juco.designsystem.component.textfield.SubManagerTextField
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun PriceInputSection(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "결제 금액 수정",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText
        )

        Spacer(Modifier.height(4.dp))

        SubManagerTextField(
            value = value,
            onValueChange = { newValue ->
                val clean = newValue.filter { it.isDigit() }
                if (clean.length <= 12) {
                    onValueChange(clean)
                }
            },
            placeholder = "결제 금액",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = remember { CommaVisualTransformation()  }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PriceInputSectionPreview() {
    SubManagerTheme {
        PriceInputSection(
            value = "",
            onValueChange = {}
        )
    }
}
package com.juco.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun SubManagerTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    placeholder: String = "내용을 입력하세요",
    isPassword: Boolean = false,
    backgroundColor: Color = SubManagerTheme.colors.secondaryBackground,
    textStyle: TextStyle = SubManagerTheme.typography.b1Regular,
    textColor: Color = SubManagerTheme.colors.primaryText,
    placeholderStyle: TextStyle = SubManagerTheme.typography.b1Regular,
    placeholderColor: Color = SubManagerTheme.colors.secondaryText,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var isFocused by remember { mutableStateOf(false) }

    val finalVisualTransformation = if (isPassword) {
        PasswordVisualTransformation()
    } else {
        visualTransformation
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { isFocused = it.isFocused },
        textStyle = textStyle.copy(color = textColor),
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = finalVisualTransformation,
        cursorBrush = SolidColor(textColor),
        interactionSource = remember { MutableInteractionSource() },
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .height(42.dp)
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = if (isFocused) SubManagerTheme.colors.primaryText
                        else SubManagerTheme.colors.primaryText,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = placeholderStyle,
                            color = placeholderColor
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun SubManagerTextFieldPreview() {
    SubManagerTheme {
        SubManagerTextField(
            value = "",
            onValueChange = {},
            placeholder = "서비스 이름을 입력해주세요"
        )
    }
}
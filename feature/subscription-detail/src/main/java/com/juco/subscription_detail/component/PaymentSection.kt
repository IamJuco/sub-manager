package com.juco.subscription_detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun PaymentSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "결제 일",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "26년 1월 10일",
            style = SubManagerTheme.typography.b2Regular,
            color = SubManagerTheme.colors.primaryText,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "결제 주기",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "1일 마다 결제 합니다.",
            style = SubManagerTheme.typography.b2Regular,
            color = SubManagerTheme.colors.primaryText,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "지금까지 결제한 총 금액",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "42,000",
            style = SubManagerTheme.typography.b2Regular,
            color = SubManagerTheme.colors.primaryText,
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentSectionPreview() {
    SubManagerTheme {
        PaymentSection()
    }
}
package com.juco.subscription_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.subscription_detail.model.SubscriptionDetailInfo
import java.text.NumberFormat

@Composable
fun PaymentSection(
    modifier: Modifier = Modifier,
    subscription: SubscriptionDetailInfo
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
            text = subscription.paymentDay ?: "",
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
            text = subscription.paymentCycle ?: "",
            style = SubManagerTheme.typography.b2Regular,
            color = SubManagerTheme.colors.primaryText,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "다음 결제 일",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = subscription.nextPaymentDate ?: "",
                style = SubManagerTheme.typography.b2Regular,
                color = SubManagerTheme.colors.primaryText,
            )

            Text(
                text = "|",
                style = SubManagerTheme.typography.b2Regular,
                color = SubManagerTheme.colors.primaryText,
            )

            Text(
                text = subscription.dDay ?: "",
                style = SubManagerTheme.typography.b2Regular,
                color = SubManagerTheme.colors.primaryText,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "지금까지 결제한 총 금액",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${NumberFormat.getIntegerInstance().format(subscription.totalAmount ?: 0)}원",
            style = SubManagerTheme.typography.b2Regular,
            color = SubManagerTheme.colors.primaryText,
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentSectionPreview() {
    SubManagerTheme {
        PaymentSection(
            subscription = SubscriptionDetailInfo(
                subId = 1L,
                name = "Netflix",
                thumbnail = "",
                price = 13500L,
                paymentDay = "26년 1월 10일",
                nextPaymentDate = "26년 1월 10일",
                dDay = "D-2",
                description = "프리미엄 멤버십",
                enableNotification = true,
                isPaused = false
            )
        )
    }
}
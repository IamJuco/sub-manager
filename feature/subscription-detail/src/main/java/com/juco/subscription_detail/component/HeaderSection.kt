package com.juco.subscription_detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.subscription_detail.model.SubscriptionDetailInfo
import java.text.NumberFormat

@Composable
fun HeaderSection(
    modifier: Modifier = Modifier,
    subscription: SubscriptionDetailInfo
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(SubManagerTheme.colors.secondaryBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = subscription.name?.take(1) ?: "",
                    style = SubManagerTheme.typography.h3SemiBold,
                    color = SubManagerTheme.colors.primaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = subscription.name ?: "",
                    style = SubManagerTheme.typography.h3SemiBold,
                    color = SubManagerTheme.colors.primaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "${NumberFormat.getIntegerInstance().format(subscription.price)}원",
                    style = SubManagerTheme.typography.h3SemiBold,
                    color = SubManagerTheme.colors.secondaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "메모",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = subscription.description ?: "",
            style = SubManagerTheme.typography.b2Regular,
            color = SubManagerTheme.colors.primaryText,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HeaderSectionPreview() {
    SubManagerTheme {
        HeaderSection(
            subscription = SubscriptionDetailInfo(
                subId = 1L,
                name = "Netflix",
                thumbnail = "",
                price = 13500L,
                paymentDay = "2026. 01. 10",
                nextPaymentDate = "26년 1월 10일",
                dDay = "D-2",
                description = "프리미엄 멤버십",
                enableNotification = true,
                isPaused = false
            )
        )
    }
}
package com.juco.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.juco.designsystem.util.QuickStartDefaultItem
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.home.model.SubscriptionInfo
import com.juco.submanager.core.designsystem.R

@Composable
fun NextPaymentItem(subscription: SubscriptionInfo) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(SubManagerTheme.colors.primaryBackground)
                .border(1.dp, SubManagerTheme.colors.secondaryText.copy(alpha = 0.2f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (QuickStartDefaultItem.isDefaultIcon(subscription.thumbnail ?: "")) {
                Image(
                    modifier = Modifier.padding(14.dp),
                    painter = painterResource(id = QuickStartDefaultItem.getResIdByKey(subscription.thumbnail ?: "")),
                    contentDescription = null
                )
            } else {
                AsyncImage(
                    modifier = Modifier.padding(14.dp),
                    model = subscription.thumbnail,
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.ic_app_logo),
                    error = painterResource(R.drawable.ic_app_logo)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = subscription.name ?: "",
                style = SubManagerTheme.typography.b1SemiBold,
                color = SubManagerTheme.colors.primaryText
            )
            if (!subscription.description.isNullOrEmpty()) {
                Text(
                    text = subscription.description,
                    style = SubManagerTheme.typography.c1Regular,
                    color = SubManagerTheme.colors.secondaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Text(
            text = "${java.text.NumberFormat.getIntegerInstance().format(subscription.price)}원",
            style = SubManagerTheme.typography.b1Regular,
            color = SubManagerTheme.colors.primaryText
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NextPaymentItemPreview() {
    SubManagerTheme {
        NextPaymentItem(
            subscription = SubscriptionInfo(
                name = "Netflix",
                thumbnail = "NETFLIX",
                price = 17000,
                description = "프리미엄",
                nextPaymentDate = "26년 1월 7일",
                dDay = "D-2",
                paymentDay = 1
            )
        )
    }
}
package com.juco.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import java.text.NumberFormat

@Composable
fun SubscriptionItem(
    subscription: SubscriptionInfo,
    onClick: () -> Unit
) {
    val borderColor = if (subscription.isPaused) {
        SubManagerTheme.colors.disabledText
    } else {
        SubManagerTheme.colors.primaryText
    }

    val contentAlpha = if (subscription.isPaused) 0.5f else 1f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = SubManagerTheme.colors.secondaryBackground,
            )
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (subscription.isPaused) {
                Text(
                    text = "일시정지 상태입니다.",
                    style = SubManagerTheme.typography.b1SemiBold,
                    color = SubManagerTheme.colors.secondaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Box(
                    modifier = Modifier
                        .background(
                            color = SubManagerTheme.colors.disabledText,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "PAUSE",
                        style = SubManagerTheme.typography.c1SemiBold,
                        color = SubManagerTheme.colors.primaryBackground
                    )
                }
            } else {
                Text(
                    text = subscription.nextPaymentDate ?: "",
                    style = SubManagerTheme.typography.b1SemiBold,
                    color = SubManagerTheme.colors.primaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Box(
                    modifier = Modifier
                        .background(
                            color = SubManagerTheme.colors.primaryText,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = subscription.dDay ?: "",
                        style = SubManagerTheme.typography.c1SemiBold,
                        color = SubManagerTheme.colors.primaryBackground
                    )
                }
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(contentAlpha),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
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
                        model = subscription.thumbnail,
                        contentDescription = null,
                        placeholder = painterResource(R.drawable.ic_app_logo),
                        error = painterResource(R.drawable.ic_app_logo)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = subscription.name ?: "",
                    style = SubManagerTheme.typography.b1SemiBold,
                    color = SubManagerTheme.colors.primaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                if (!subscription.description.isNullOrEmpty()) {
                    Text(
                        text = subscription.description,
                        style = SubManagerTheme.typography.c1Regular,
                        color = SubManagerTheme.colors.secondaryText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                val priceText = subscription.price?.let {
                    NumberFormat.getIntegerInstance().format(it)
                } ?: "0"

                Text(
                    text = "${priceText}원",
                    style = SubManagerTheme.typography.c1Regular,
                    color = SubManagerTheme.colors.secondaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SubscriptionItemPreview() {
    SubManagerTheme {
        SubscriptionItem(
            subscription = SubscriptionInfo(
                name = "Netflix",
                thumbnail = "NETFLIX",
                price = 17000,
                description = "프리미엄",
                nextPaymentDate = "26년 1월 7일",
                dDay = "D-2",
                paymentDay = 1
            ),
            onClick = {}
        )
    }
}
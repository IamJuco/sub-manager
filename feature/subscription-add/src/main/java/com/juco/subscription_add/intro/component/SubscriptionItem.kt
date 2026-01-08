package com.juco.subscription_add.intro.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.common.model.SubscriptionQuickStartInfo
import com.juco.designsystem.theme.SubManagerTheme
import java.text.NumberFormat

@Composable
fun SubscriptionItem(
    subscription: SubscriptionQuickStartInfo,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                color = SubManagerTheme.colors.primaryText,
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(SubManagerTheme.colors.primaryBackground),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = subscription.name.take(1),
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
                text = subscription.name,
                style = SubManagerTheme.typography.b1SemiBold,
                color = SubManagerTheme.colors.primaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(2.dp))


            Text(
                text = subscription.description ?: "메모",
                style = SubManagerTheme.typography.c1Regular,
                color = SubManagerTheme.colors.secondaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${NumberFormat.getIntegerInstance().format(subscription.price)}원",
                style = SubManagerTheme.typography.c1Regular,
                color = SubManagerTheme.colors.secondaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SubscriptionItemPreview() {
    SubManagerTheme {
        SubscriptionItem(
            SubscriptionQuickStartInfo(
                name = "Netflix",
                thumbnail = "NETFLIX",
                price = 17000,
                description = "프리미엄 4K"
            ),
            onClick = {}
        )
    }
}
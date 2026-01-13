package com.juco.subscription_edit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import com.juco.subscription_edit.model.SubscriptionInfo

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    subscription: SubscriptionInfo
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "프로필 수정",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText
        )

        Spacer(Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(SubManagerTheme.colors.secondaryBackground),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = subscription.name?.take(1) ?: "n",
                style = SubManagerTheme.typography.h3SemiBold,
                color = SubManagerTheme.colors.primaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileSectionPreview() {
    SubManagerTheme {
        ProfileSection(
            subscription = SubscriptionInfo()
        )
    }
}
package com.juco.subscription_edit.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.juco.submanager.core.designsystem.R
import com.juco.subscription_edit.model.SubscriptionInfo

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    subscription: SubscriptionInfo
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
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
                .background(SubManagerTheme.colors.primaryBackground)
                .border(1.dp, SubManagerTheme.colors.secondaryText.copy(alpha = 0.2f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (QuickStartDefaultItem.isDefaultIcon(subscription.thumbnail ?: "")) {
                Image(
                    modifier = Modifier.padding(14.dp),
                    painter = painterResource(
                        id = QuickStartDefaultItem.getResIdByKey(
                            subscription.thumbnail ?: ""
                        )
                    ),
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
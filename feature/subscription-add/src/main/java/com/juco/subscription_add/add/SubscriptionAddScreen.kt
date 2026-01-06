package com.juco.subscription_add.add

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.juco.designsystem.theme.SubManagerTheme

@Composable
fun SubscriptionAddRoute(
    padding: PaddingValues
) {
    SubscriptionAddScreen(
        padding = padding
    )
}

@Composable
fun SubscriptionAddScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues
) {

}

@Preview(showBackground = true)
@Composable
private fun SubscriptionAddScreenPreview() {
    SubManagerTheme {
        SubscriptionAddScreen(
            padding = PaddingValues()
        )
    }
}
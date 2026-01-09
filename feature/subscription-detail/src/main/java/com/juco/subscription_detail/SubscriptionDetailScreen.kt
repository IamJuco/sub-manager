package com.juco.subscription_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.component.SubManagerTopBar
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.submanager.core.designsystem.R

@Composable
fun SubscriptionDetailRoute(
    padding: PaddingValues,
    onPopBackStack: () -> Unit
) {
    SubscriptionDetailScreen(
        padding = padding,
        onPopBackStack = onPopBackStack
    )
}

@Composable
fun SubscriptionDetailScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    onPopBackStack: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SubManagerTheme.colors.primaryBackground)
            .padding(padding)
            .padding(horizontal = 16.dp)
    ) {
        SubManagerTopBar(
            modifier = Modifier.padding(top = 16.dp),
            title = "상세 정보",
            iconRes = R.drawable.ic_chevron_left_fill_true,
            onPopBackStack = onPopBackStack
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SubscriptionDetailScreenPreview() {
    SubManagerTheme {
        SubscriptionDetailScreen(
            padding = PaddingValues(),
            onPopBackStack = {}
        )
    }
}
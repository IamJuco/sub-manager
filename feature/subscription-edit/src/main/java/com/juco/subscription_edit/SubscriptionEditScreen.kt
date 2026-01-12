package com.juco.subscription_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.designsystem.component.SubManagerTopBar
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.submanager.core.designsystem.R

@Composable
fun SubscriptionEditRoute(
    padding: PaddingValues,
    onPopBackStack: () -> Unit,
    subId: Long
) {
    SubscriptionEditScreen(
        padding = padding,
        onPopBackStack = onPopBackStack,
        subId = subId
    )
}

@Composable
fun SubscriptionEditScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    onPopBackStack: () -> Unit,
    subId: Long
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SubManagerTheme.colors.primaryBackground)
            .padding(padding)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SubManagerTopBar(
            modifier = Modifier.padding(top = 16.dp),
            title = "서비스 수정",
            iconRes = R.drawable.ic_chevron_left_fill_true,
            onPopBackStack = onPopBackStack
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun SubscriptionEditScreenPreview() {
    SubManagerTheme {
        SubscriptionEditScreen(
            padding = PaddingValues(),
            onPopBackStack = {},
            subId = 0L
        )
    }
}
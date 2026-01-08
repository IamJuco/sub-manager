package com.juco.subscription_add.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.common.model.SubscriptionQuickStartInfo
import com.juco.designsystem.component.SubManagerTopBar
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.submanager.core.designsystem.R
import com.juco.subscription_add.intro.component.SelfAddButton
import com.juco.subscription_add.intro.component.SubscriptionItem
import com.juco.subscription_add.intro.dataset.quickStartSubscriptionList

@Composable
fun SubscriptionAddIntroRoute(
    padding: PaddingValues,
    navigateToSubscriptionAdd: (SubscriptionQuickStartInfo?) -> Unit,
    onPopBackStack: () -> Unit,
) {
    SubscriptionAddIntroScreen(
        padding = padding,
        navigateToSubscriptionAdd = navigateToSubscriptionAdd,
        onPopBackStack = onPopBackStack
    )
}

@Composable
fun SubscriptionAddIntroScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    navigateToSubscriptionAdd: (SubscriptionQuickStartInfo?) -> Unit,
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
            title = "구독 추가 메뉴",
            iconRes = R.drawable.ic_chevron_left_fill_true,
            onPopBackStack = onPopBackStack
        )

        Spacer(Modifier.height(16.dp))

        SelfAddButton(
            onClick = { navigateToSubscriptionAdd(null) }
        )

        Spacer(Modifier.height(16.dp))

        HorizontalDivider()

        Spacer(Modifier.height(16.dp))

        SubManagerTopBar(
            title = "빠른 시작 메뉴"
        )

        Spacer(Modifier.height(16.dp))

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(quickStartSubscriptionList) { item ->
                SubscriptionItem(
                    subscription = item,
                    onClick = { navigateToSubscriptionAdd(item) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubscriptionAddScreenPreview() {
    SubManagerTheme {
        SubscriptionAddIntroScreen(
            padding = PaddingValues(),
            navigateToSubscriptionAdd = {},
            onPopBackStack = {}
        )
    }
}
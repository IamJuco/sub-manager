package com.juco.subscription_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.juco.designsystem.component.SubManagerTopBar
import com.juco.designsystem.component.loading.SubManagerLoadingBar
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.local.model.Subscription
import com.juco.submanager.core.designsystem.R
import com.juco.subscription_detail.component.HeaderSection
import com.juco.subscription_detail.component.NotificationSection
import com.juco.subscription_detail.component.PaymentSection
import com.juco.subscription_detail.component.PauseSection
import com.juco.subscription_detail.component.SettingSection
import com.juco.subscription_detail.state.SubscriptionDetailUiState

@Composable
fun SubscriptionDetailRoute(
    padding: PaddingValues,
    onPopBackStack: () -> Unit,
    subId: Long,
    viewModel: SubscriptionDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(subId) {
        viewModel.loadSubscription(subId)
    }

    when (val state = uiState) {
        is SubscriptionDetailUiState.Loading -> {
            SubManagerLoadingBar()
        }

        is SubscriptionDetailUiState.Success -> {
            SubscriptionDetailScreen(
                padding = padding,
                onPopBackStack = onPopBackStack,
                subscription = state.subscription
            )
        }

        is SubscriptionDetailUiState.Error -> {
            LaunchedEffect(Unit) {
                onPopBackStack()
            }
        }
    }
}

@Composable
fun SubscriptionDetailScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    onPopBackStack: () -> Unit,
    subscription: Subscription
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
            title = "상세 정보",
            iconRes = R.drawable.ic_chevron_left_fill_true,
            onPopBackStack = onPopBackStack
        )

        Spacer(modifier = Modifier.height(16.dp))

        HeaderSection(
            thumbnail = subscription.thumbnail,
            name = subscription.name,
            price = subscription.price,
            description = subscription.description ?: ""
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = SubManagerTheme.colors.primaryText,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        PaymentSection()

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = SubManagerTheme.colors.primaryText,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        NotificationSection(
            isChecked = false,
            onCheckedChange = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = SubManagerTheme.colors.primaryText,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        PauseSection(
            isChecked = false,
            onCheckedChange = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = SubManagerTheme.colors.primaryText,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingSection()
    }
}

@Preview(showBackground = true)
@Composable
private fun SubscriptionDetailScreenPreview() {
    SubManagerTheme {
        SubscriptionDetailScreen(
            padding = PaddingValues(),
            onPopBackStack = {},
            subscription = Subscription(
                subId = 1L,
                name = "Netflix",
                thumbnail = "",
                price = 13500L,
                paymentDay = System.currentTimeMillis(),
                paymentCycleType = "MONTH",
                paymentCycleValue = 1,
                description = "프리미엄 멤버십",
                enableNotification = true
            )
        )
    }
}
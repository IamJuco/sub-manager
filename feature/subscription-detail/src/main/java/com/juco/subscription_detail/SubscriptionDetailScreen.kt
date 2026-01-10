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
import com.juco.submanager.core.designsystem.R
import com.juco.subscription_detail.component.HeaderSection
import com.juco.subscription_detail.component.NotificationSection
import com.juco.subscription_detail.component.PaymentSection
import com.juco.subscription_detail.component.PauseSection
import com.juco.subscription_detail.component.SettingSection
import com.juco.subscription_detail.model.SubscriptionDetailInfo
import com.juco.subscription_detail.state.SubscriptionDetailUiState

@Composable
fun SubscriptionDetailRoute(
    padding: PaddingValues,
    onPopBackStack: () -> Unit,
    onShowSnackBar: (String) -> Unit,
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
                subscription = state.subscription,
                onNotificationToggle = viewModel::updateNotification,
                onPauseToggle = viewModel::updatePause,
                onShowSnackBar = onShowSnackBar
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
    onShowSnackBar: (String) -> Unit,
    subscription: SubscriptionDetailInfo,
    onNotificationToggle: (Boolean) -> Unit,
    onPauseToggle: (Boolean) -> Unit
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
            subscription = subscription
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = SubManagerTheme.colors.primaryText,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        PaymentSection(
            subscription = subscription
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = SubManagerTheme.colors.primaryText,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        NotificationSection(
            isChecked = subscription.enableNotification,
            onCheckedChange = { isEnabled ->
                if (isEnabled && subscription.isPaused) {
                    onShowSnackBar("일시정지 중에는 알람을 켤 수 없습니다.")
                } else {
                    onNotificationToggle(isEnabled)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = SubManagerTheme.colors.primaryText,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        PauseSection(
            isChecked = subscription.isPaused,
            onCheckedChange = { isPaused ->
                if (isPaused && subscription.enableNotification) {
                    onShowSnackBar("일시정지되어 알림 설정이 해제되었습니다.")
                }
                onPauseToggle(isPaused)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = SubManagerTheme.colors.primaryText,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingSection()

        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun SubscriptionDetailScreenPreview() {
    SubManagerTheme {
        SubscriptionDetailScreen(
            padding = PaddingValues(),
            onPopBackStack = {},
            subscription = SubscriptionDetailInfo(
                subId = 1L,
                name = "Netflix",
                thumbnail = "",
                price = 13500L,
                paymentDay = "2026. 01. 10",
                nextPaymentDate = "26년 1월 10일",
                dDay = "D-2",
                description = "프리미엄 멤버십",
                enableNotification = true,
                isPaused = false
            ),
            onNotificationToggle = {},
            onPauseToggle = {},
            onShowSnackBar = {}
        )
    }
}
package com.juco.subscription_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.juco.designsystem.component.SubManagerTopBar
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.submanager.core.designsystem.R
import com.juco.subscription_edit.component.DescriptionSection
import com.juco.subscription_edit.component.NameInputSection
import com.juco.subscription_edit.component.PaymentCycleSection
import com.juco.subscription_edit.component.PaymentDateSection
import com.juco.subscription_edit.component.PriceInputSection
import com.juco.subscription_edit.component.ProfileSection
import com.juco.subscription_edit.model.SubscriptionInfo

@Composable
fun SubscriptionEditRoute(
    padding: PaddingValues,
    onPopBackStack: () -> Unit,
    subId: Long,
    viewModel: SubscriptionEditViewModel = hiltViewModel()
) {

    LaunchedEffect(subId) {
        viewModel.loadSubscription(subId)
    }

    SubscriptionEditScreen(
        padding = padding,
        onPopBackStack = onPopBackStack,
        subscription = SubscriptionInfo()
    )
}

@Composable
fun SubscriptionEditScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    onPopBackStack: () -> Unit,
    subscription: SubscriptionInfo
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
            title = "구독 서비스 수정",
            iconRes = R.drawable.ic_chevron_left_fill_true,
            onPopBackStack = onPopBackStack
        )

        Spacer(Modifier.height(32.dp))

        ProfileSection(
            subscription = subscription
        )

        Spacer(Modifier.height(32.dp))

        NameInputSection(
            value = "",
            onValueChange = {}
        )

        Spacer(Modifier.height(32.dp))

        DescriptionSection(
            value = "",
            onValueChange = {}
        )

        Spacer(Modifier.height(32.dp))

        PriceInputSection(
            value = "",
            onValueChange = {}
        )

        Spacer(Modifier.height(32.dp))

        PaymentDateSection(
            selectedDate = 0,
            onDateChanged = {}
        )

        Spacer(Modifier.height(32.dp))

        PaymentCycleSection(
            paymentCycle = "",
            onValueChange = {}
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
            subscription = SubscriptionInfo()
        )
    }
}
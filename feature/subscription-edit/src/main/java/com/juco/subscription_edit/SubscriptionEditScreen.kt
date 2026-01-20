package com.juco.subscription_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.juco.designsystem.component.SubManagerTopBar
import com.juco.designsystem.component.button.SubManagerButton
import com.juco.designsystem.component.loading.SubManagerLoadingBar
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.submanager.core.designsystem.R
import com.juco.subscription_edit.component.DescriptionSection
import com.juco.subscription_edit.component.NameInputSection
import com.juco.subscription_edit.component.PaymentCycleSection
import com.juco.subscription_edit.component.PaymentDateSection
import com.juco.subscription_edit.component.PriceInputSection
import com.juco.subscription_edit.component.ProfileSection
import com.juco.subscription_edit.mapper.toPaymentCycle
import com.juco.subscription_edit.model.SubscriptionInfo
import com.juco.subscription_edit.sideeffect.SubscriptionEditSideEffect
import com.juco.subscription_edit.state.SubscriptionEditUiState

@Composable
fun SubscriptionEditRoute(
    padding: PaddingValues,
    onPopBackStack: () -> Unit,
    subId: Long,
    onShowSnackBar: (String) -> Unit,
    viewModel: SubscriptionEditViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(subId) {
        viewModel.loadSubscription(subId)
    }

    LaunchedEffect(viewModel.sideEffectFlow) {
        viewModel.sideEffectFlow.collect { event ->
            when (event) {
                is SubscriptionEditSideEffect.ShowSnackBar -> onShowSnackBar(event.message)
                is SubscriptionEditSideEffect.UpdateSuccess -> onPopBackStack()
            }
        }
    }

    when (val state = uiState) {
        is SubscriptionEditUiState.Loading -> {
            SubManagerLoadingBar()
        }
        is SubscriptionEditUiState.Error -> {
            LaunchedEffect(Unit) { onPopBackStack() }
        }
        is SubscriptionEditUiState.Success -> {
            Box(modifier = Modifier.fillMaxSize()) {
                SubscriptionEditScreen(
                    padding = padding,
                    onPopBackStack = onPopBackStack,
                    subscription = state.subscription,
                    onUpdateClick = viewModel::updateSubscription,
                    onShowSnackBar = onShowSnackBar
                )

                if (state.isUpdating) {
                    SubManagerLoadingBar()
                }
            }
        }
    }
}

@Composable
fun SubscriptionEditScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    onPopBackStack: () -> Unit,
    subscription: SubscriptionInfo,
    onUpdateClick: (SubscriptionInfo) -> Unit,
    onShowSnackBar: (String) -> Unit
) {
    var name by remember(subscription) { mutableStateOf(subscription.name ?: "") }
    var description by remember(subscription) { mutableStateOf(subscription.description ?: "") }
    var price by remember(subscription) { mutableStateOf(subscription.price?.toString() ?: "") }
    var selectedDate by remember(subscription) { mutableLongStateOf(subscription.paymentDay ?: 0L) }
    var paymentCycle by remember(subscription) { mutableStateOf(subscription.toPaymentCycle()) }
    var thumbnail by remember(subscription) { mutableStateOf(subscription.thumbnail ?: "") }

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
            thumbnail = thumbnail,
            onClickThumbnailChange = { thumbnail = it },
            onShowSnackBar = onShowSnackBar
        )

        Spacer(Modifier.height(32.dp))

        NameInputSection(
            value = name,
            onValueChange = { name = it }
        )

        Spacer(Modifier.height(32.dp))

        DescriptionSection(
            value = description,
            onValueChange = { description = it }
        )

        Spacer(Modifier.height(32.dp))

        PriceInputSection(
            value = price,
            onValueChange = { price = it }
        )

        Spacer(Modifier.height(32.dp))

        PaymentDateSection(
            selectedDate = selectedDate,
            onDateChanged = { selectedDate = it }
        )

        Spacer(Modifier.height(32.dp))

        PaymentCycleSection(
            paymentCycle = paymentCycle.toDisplayText(),
            onValueChange = { paymentCycle = it }
        )

        Spacer(Modifier.height(32.dp))

        HorizontalDivider(
            color = SubManagerTheme.colors.primaryText,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(32.dp))

        SubManagerButton(
            modifier = Modifier.fillMaxWidth(),
            text = "수정 완료",
            onClick = {
                onUpdateClick(
                    SubscriptionInfo(
                        subId = subscription.subId,
                        thumbnail = thumbnail,
                        name = name,
                        description = description,
                        price = price.toLongOrNull() ?: 0L,
                        paymentDay = selectedDate,
                        paymentCycleType = paymentCycle.type.name,
                        paymentCycleValue = paymentCycle.value
                    )
                )
            }
        )

        Spacer(Modifier.height(50.dp))

    }
}

@Preview(showBackground = true)
@Composable
private fun SubscriptionEditScreenPreview() {
    SubManagerTheme {
        SubscriptionEditScreen(
            padding = PaddingValues(),
            onPopBackStack = {},
            subscription = SubscriptionInfo(),
            onUpdateClick = {},
            onShowSnackBar = {}
        )
    }
}
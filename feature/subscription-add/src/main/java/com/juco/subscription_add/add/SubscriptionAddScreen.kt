package com.juco.subscription_add.add

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.juco.common.util.PaymentCycle
import com.juco.designsystem.component.SubManagerTopBar
import com.juco.designsystem.component.button.SubManagerButton
import com.juco.designsystem.component.loading.SubManagerLoadingBar
import com.juco.designsystem.theme.SubManagerTheme
import com.juco.submanager.core.designsystem.R
import com.juco.subscription_add.add.component.NameInputSection
import com.juco.subscription_add.add.component.PaymentCycleSection
import com.juco.subscription_add.add.component.PaymentDateSection
import com.juco.subscription_add.add.component.PriceInputSection
import com.juco.subscription_add.add.model.InputStep
import com.juco.subscription_add.add.model.SubscriptionAdd
import com.juco.subscription_add.add.sideeffect.SubscriptionAddSideEffect

@Composable
fun SubscriptionAddRoute(
    padding: PaddingValues,
    onShowSnackBar: (String) -> Unit,
    navigateToHome: () -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: SubscriptionAddViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is SubscriptionAddSideEffect.NavigateToHome -> {
                    onShowSnackBar("정보가 저장 되었습니다.")
                    navigateToHome()
                }
                is SubscriptionAddSideEffect.ShowSnackBar -> {
                    onShowSnackBar(effect.message)
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SubscriptionAddScreen(
            padding = padding,
            onPopBackStack = onPopBackStack,
            onSaveClick = {
                viewModel.saveSubscription(
                    subscriptionAdd = it
                )
            }
        )
        if (uiState.isLoading) {
            SubManagerLoadingBar()
        }
    }

}

@Composable
fun SubscriptionAddScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    onPopBackStack: () -> Unit,
    onSaveClick: (SubscriptionAdd) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var selectedDate by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var paymentCycle by remember { mutableStateOf(PaymentCycle()) }

    var currentStep by remember { mutableStateOf(InputStep.NAME) }
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    val nameFocusRequester = remember { FocusRequester() }
    val priceFocusRequester = remember { FocusRequester() }

    LaunchedEffect(currentStep) {
        scrollState.animateScrollTo(scrollState.maxValue)
        when (currentStep) {
            InputStep.NAME -> nameFocusRequester.requestFocus()
            InputStep.PRICE -> priceFocusRequester.requestFocus()
            else -> focusManager.clearFocus()
        }
    }

    LaunchedEffect(Unit) {
        nameFocusRequester.requestFocus()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SubManagerTheme.colors.primaryBackground)
            .padding(padding)
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState)
    ) {
        SubManagerTopBar(
            modifier = Modifier.padding(top = 16.dp),
            title = "구독 서비스 추가",
            iconRes = R.drawable.ic_chevron_left_fill_true,
            onPopBackStack = onPopBackStack
        )

        Spacer(Modifier.height(32.dp))

        NameInputSection(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.focusRequester(nameFocusRequester)
        )

        if (currentStep.ordinal >= InputStep.PRICE.ordinal) {
            Spacer(Modifier.height(32.dp))
            PriceInputSection(
                value = price,
                onValueChange = { price = it },
                modifier = Modifier.focusRequester(priceFocusRequester)
            )
        }

        if (currentStep.ordinal >= InputStep.DATE.ordinal) {
            Spacer(Modifier.height(32.dp))
            PaymentDateSection(
                selectedDate = selectedDate,
                onDateChanged = { newDate -> selectedDate = newDate }
            )
        }

        if (currentStep.ordinal >= InputStep.CYCLE.ordinal) {
            Spacer(Modifier.height(32.dp))
            PaymentCycleSection(
                paymentCycleText = paymentCycle.toDisplayText(),
                onValueChange = { newCycle -> paymentCycle = newCycle }
            )
        }

        Spacer(Modifier.height(50.dp))

        SubManagerButton(
            modifier = Modifier.fillMaxWidth(),
            text = if (currentStep == InputStep.CYCLE) "완료" else "다음",
            enabled = when (currentStep) {
                InputStep.NAME -> name.isNotEmpty()
                InputStep.PRICE -> price.isNotEmpty()
                else -> true
            },
            onClick = {
                when (currentStep) {
                    InputStep.NAME -> currentStep = InputStep.PRICE
                    InputStep.PRICE -> currentStep = InputStep.DATE
                    InputStep.DATE -> currentStep = InputStep.CYCLE
                    InputStep.CYCLE -> {
                        onSaveClick(
                            SubscriptionAdd(
                                name = name,
                                price = price.toLongOrNull(),
                                paymentDay = selectedDate,
                                paymentCycleType = paymentCycle.type.name,
                                paymentCycleValue = paymentCycle.value
                            )
                        )
                    }
                }
            }
        )

        Spacer(Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun SubscriptionAddScreenPreview() {
    SubManagerTheme {
        SubscriptionAddScreen(
            padding = PaddingValues(),
            onPopBackStack = {},
            onSaveClick = { SubscriptionAdd() }
        )
    }
}
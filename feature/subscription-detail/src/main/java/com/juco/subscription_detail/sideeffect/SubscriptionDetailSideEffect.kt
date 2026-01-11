package com.juco.subscription_detail.sideeffect

sealed interface SubscriptionDetailSideEffect {
    data object NavigateToHome : SubscriptionDetailSideEffect
    data object ShowSnackBar : SubscriptionDetailSideEffect
}
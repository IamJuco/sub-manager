package com.juco.subscription_add.add.sideeffect

sealed interface SubscriptionAddSideEffect {
    data object NavigateToHome : SubscriptionAddSideEffect
    data class ShowSnackBar(val message: String) : SubscriptionAddSideEffect
}
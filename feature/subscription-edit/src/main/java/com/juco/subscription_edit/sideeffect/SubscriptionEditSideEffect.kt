package com.juco.subscription_edit.sideeffect

sealed interface SubscriptionEditSideEffect {
    data class ShowSnackBar(val message: String) : SubscriptionEditSideEffect
    data object UpdateSuccess : SubscriptionEditSideEffect
}
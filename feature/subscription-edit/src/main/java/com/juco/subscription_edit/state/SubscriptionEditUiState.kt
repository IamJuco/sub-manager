package com.juco.subscription_edit.state

import com.juco.subscription_edit.model.SubscriptionInfo

sealed interface SubscriptionEditUiState {
    data object Loading : SubscriptionEditUiState
    data class Success(
        val subscription: SubscriptionInfo,
        val isUpdating: Boolean = false
    ) : SubscriptionEditUiState
    data object Error : SubscriptionEditUiState
}
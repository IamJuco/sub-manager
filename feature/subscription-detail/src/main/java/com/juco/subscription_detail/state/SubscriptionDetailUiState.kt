package com.juco.subscription_detail.state

import com.juco.local.model.Subscription

sealed interface SubscriptionDetailUiState {
    data object Loading : SubscriptionDetailUiState
    data class Success(val subscription: Subscription) : SubscriptionDetailUiState
    data object Error : SubscriptionDetailUiState
}
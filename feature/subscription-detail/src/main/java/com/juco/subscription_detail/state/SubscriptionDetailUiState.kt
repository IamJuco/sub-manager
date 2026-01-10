package com.juco.subscription_detail.state

import com.juco.subscription_detail.model.SubscriptionDetailInfo

sealed interface SubscriptionDetailUiState {
    data object Loading : SubscriptionDetailUiState
    data class Success(val subscription: SubscriptionDetailInfo) : SubscriptionDetailUiState
    data object Error : SubscriptionDetailUiState
}
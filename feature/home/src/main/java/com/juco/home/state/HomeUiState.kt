package com.juco.home.state

import com.juco.home.model.NextPaymentInfo
import com.juco.home.model.SubscriptionInfo

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data object Empty : HomeUiState
    data class Success(
        val subscriptionList: List<SubscriptionInfo>,
        val nextPaymentInfo: NextPaymentInfo?
    ) : HomeUiState
}
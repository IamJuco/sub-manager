package com.juco.subscription_add.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juco.common.util.Logger
import com.juco.local.model.Subscription
import com.juco.local.repository.LocalRepository
import com.juco.subscription_add.add.model.SubscriptionAdd
import com.juco.subscription_add.add.sideeffect.SubscriptionAddSideEffect
import com.juco.subscription_add.add.state.SubscriptionAddUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubscriptionAddViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SubscriptionAddUiState())
    val state = _uiState.asStateFlow()

    private val _sideEffect = Channel<SubscriptionAddSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun saveSubscription(
        subscriptionAdd: SubscriptionAdd
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                val subscription = Subscription(
                    name = subscriptionAdd.name ?: "",
                    thumbnail = "",
                    price = subscriptionAdd.price ?: 0,
                    paymentDay = subscriptionAdd.paymentDay ?: 0,
                    paymentCycleType = subscriptionAdd.paymentCycleType ?: "",
                    paymentCycleValue = subscriptionAdd.paymentCycleValue ?: 0,
                    description = subscriptionAdd.description,
                    enableNotification = subscriptionAdd.enableNotification,
                    isPaused = false
                )
                localRepository.insertSubscription(subscription)

            }.onSuccess {
                _sideEffect.send(SubscriptionAddSideEffect.NavigateToHome)

            }.onFailure { e ->
                _sideEffect.send(SubscriptionAddSideEffect.ShowSnackBar("저장에 실패했습니다."))
                Logger.e("0526SubAddViewModel", e.message.toString())

            }
            _uiState.update { it.copy(isLoading = false) }

        }
    }
}
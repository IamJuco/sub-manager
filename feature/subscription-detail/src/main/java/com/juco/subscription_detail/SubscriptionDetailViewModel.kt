package com.juco.subscription_detail

import androidx.lifecycle.ViewModel
import com.juco.local.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.juco.common.util.PaymentCycle
import com.juco.common.util.PaymentCycleType
import com.juco.common.util.formatDate
import com.juco.common.util.nextPaymentCalculator
import com.juco.common.util.totalAmountCalculator
import com.juco.subscription_detail.model.SubscriptionDetailInfo
import com.juco.subscription_detail.sideeffect.SubscriptionDetailSideEffect
import com.juco.subscription_detail.state.SubscriptionDetailUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SubscriptionDetailViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<SubscriptionDetailUiState>(SubscriptionDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SubscriptionDetailSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun loadSubscription(subId: Long) {
        viewModelScope.launch {
            _uiState.update { SubscriptionDetailUiState.Loading }
            runCatching {
                localRepository.getSubscriptionById(subId)

            }.onSuccess { subscription ->
                val cycleType =
                    PaymentCycleType.entries.find { it.name == subscription.paymentCycleType }
                        ?: PaymentCycleType.NONE
                val paymentCycle = PaymentCycle(
                    type = cycleType,
                    value = subscription.paymentCycleValue
                ).toDisplayText()
                val nextPaymentResult = nextPaymentCalculator(
                    startDateMillis = subscription.paymentDay,
                    cycleType = subscription.paymentCycleType,
                    cycleValue = subscription.paymentCycleValue
                )
                val totalAmountResult = totalAmountCalculator(
                    startDateMillis = subscription.paymentDay,
                    price = subscription.price,
                    cycleType = subscription.paymentCycleType,
                    cycleValue = subscription.paymentCycleValue
                )

                val detailInfo = SubscriptionDetailInfo(
                    subId = subscription.subId,
                    name = subscription.name,
                    thumbnail = subscription.thumbnail,
                    price = subscription.price,
                    totalAmount = totalAmountResult,
                    paymentDay = formatDate(subscription.paymentDay),
                    paymentCycle = paymentCycle,
                    nextPaymentDate = nextPaymentResult.formattedDate,
                    dDay = nextPaymentResult.dDay,
                    description = subscription.description,
                    enableNotification = subscription.enableNotification,
                    isPaused = subscription.isPaused
                )

                _uiState.update {
                    SubscriptionDetailUiState.Success(detailInfo)
                }

            }.onFailure { e ->
                _uiState.update {
                    SubscriptionDetailUiState.Error
                }
            }
        }
    }

    fun updateNotification(enableNotification: Boolean) {
        val currentState = _uiState.value
        if (currentState !is SubscriptionDetailUiState.Success) return

        val currentInfo = currentState.subscription
        val subId = currentInfo.subId ?: return

        viewModelScope.launch {
            _uiState.update {
                SubscriptionDetailUiState.Success(
                    currentInfo.copy(enableNotification = enableNotification)
                )
            }
            runCatching {
                localRepository.updateNotification(subId, enableNotification)
            }.onFailure { e ->
                _uiState.update {
                    SubscriptionDetailUiState.Success(
                        currentInfo.copy(
                            enableNotification = !enableNotification
                        )
                    )
                }
            }
        }
    }

    fun updatePause(isPaused: Boolean) {
        val currentState = _uiState.value
        if (currentState !is SubscriptionDetailUiState.Success) return
        val currentInfo = currentState.subscription
        val subId = currentInfo.subId ?: return

        val notificationState = if (isPaused) false else currentInfo.enableNotification

        viewModelScope.launch {
            _uiState.update {
                SubscriptionDetailUiState.Success(
                    currentInfo.copy(isPaused = isPaused, enableNotification = notificationState)
                )
            }

            runCatching {
                localRepository.updatePause(subId, isPaused)
                if (isPaused) {
                    localRepository.updateNotification(subId, false)
                }
            }.onFailure {
                _uiState.update {
                    SubscriptionDetailUiState.Success(currentInfo)
                }
            }
        }
    }

    fun deleteSubscription(subId: Long) {
        viewModelScope.launch {
            _uiState.update { SubscriptionDetailUiState.Loading }
            runCatching {
                localRepository.deleteSubscription(subId)
            }.onSuccess {
                _sideEffect.send(SubscriptionDetailSideEffect.NavigateToHome)
            }.onFailure {
                _sideEffect.send(SubscriptionDetailSideEffect.ShowSnackBar)
            }
        }
    }
}
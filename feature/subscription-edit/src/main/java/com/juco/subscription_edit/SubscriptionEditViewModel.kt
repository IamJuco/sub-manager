package com.juco.subscription_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juco.local.model.Subscription
import com.juco.local.repository.LocalRepository
import com.juco.subscription_edit.model.SubscriptionInfo
import com.juco.subscription_edit.sideeffect.SubscriptionEditSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.juco.subscription_edit.state.SubscriptionEditUiState
import kotlinx.coroutines.flow.update

@HiltViewModel
class SubscriptionEditViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SubscriptionEditUiState>(SubscriptionEditUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _sideEffectFlow = Channel<SubscriptionEditSideEffect>()
    val sideEffectFlow = _sideEffectFlow.receiveAsFlow()

    private var originalSubscription: Subscription? = null

    fun loadSubscription(subId: Long) {
        viewModelScope.launch {
            _uiState.update { SubscriptionEditUiState.Loading }

            runCatching {
                localRepository.getSubscriptionById(subId)
            }.onSuccess { subscription ->
                originalSubscription = subscription
                _uiState.update {
                    SubscriptionEditUiState.Success(subscription.toSubscriptionInfo())
                }
            }.onFailure {
                _uiState.update { SubscriptionEditUiState.Error }
                _sideEffectFlow.send(SubscriptionEditSideEffect.ShowSnackBar("데이터를 불러오는데 실패했습니다."))
            }
        }
    }

    fun updateSubscription(newInfo: SubscriptionInfo) {
        val currentState = _uiState.value as? SubscriptionEditUiState.Success ?: return
        val original = originalSubscription ?: return
        viewModelScope.launch {
            _uiState.update { currentState.copy(isUpdating = true) }

            runCatching {
                val mergedSubscription = mergeSubscription(original, newInfo)
                localRepository.updateSubscription(mergedSubscription)
            }.onSuccess {
                _sideEffectFlow.send(SubscriptionEditSideEffect.ShowSnackBar("수정이 완료되었습니다."))
                _sideEffectFlow.send(SubscriptionEditSideEffect.UpdateSuccess)
            }.onFailure {
                _uiState.update { currentState.copy(isUpdating = false) }
                _sideEffectFlow.send(SubscriptionEditSideEffect.ShowSnackBar("수정에 실패했습니다."))
            }
        }
    }

    private fun mergeSubscription(original: Subscription, newInfo: SubscriptionInfo): Subscription {
        return original.copy(
            name = newInfo.name ?: original.name,
            thumbnail = newInfo.thumbnail ?: original.thumbnail,
            price = newInfo.price ?: original.price,
            paymentDay = newInfo.paymentDay ?: original.paymentDay,
            paymentCycleType = newInfo.paymentCycleType ?: original.paymentCycleType,
            paymentCycleValue = newInfo.paymentCycleValue ?: original.paymentCycleValue,
            description = newInfo.description,
            subId = original.subId,
            enableNotification = original.enableNotification,
            isPaused = original.isPaused
        )
    }

    private fun Subscription.toSubscriptionInfo(): SubscriptionInfo {
        return SubscriptionInfo(
            subId = this.subId,
            name = this.name,
            thumbnail = this.thumbnail,
            price = this.price,
            paymentDay = this.paymentDay,
            paymentCycleType = this.paymentCycleType,
            paymentCycleValue = this.paymentCycleValue,
            description = this.description
        )
    }
}
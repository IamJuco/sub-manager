package com.juco.subscription_detail

import androidx.lifecycle.ViewModel
import com.juco.local.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.juco.subscription_detail.state.SubscriptionDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SubscriptionDetailViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SubscriptionDetailUiState>(SubscriptionDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadSubscription(subId: Long) {
        viewModelScope.launch {
            _uiState.update { SubscriptionDetailUiState.Loading }
            runCatching {
                localRepository.getSubscriptionById(subId)

            }.onSuccess { subscription ->
                _uiState.update {
                    SubscriptionDetailUiState.Success(subscription)
                }
                
            }.onFailure { e ->
                _uiState.update {
                    SubscriptionDetailUiState.Error
                }
            }
        }
    }
}
package com.juco.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juco.common.util.nextPaymentCalculator
import com.juco.home.model.NextPaymentInfo
import com.juco.home.model.SubscriptionInfo
import com.juco.home.state.HomeUiState
import com.juco.local.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    localRepository: LocalRepository
) : ViewModel() {
    val uiState: StateFlow<HomeUiState> = localRepository.getAllSubscriptions()
        .map { list ->
            if (list.isEmpty()) {
                HomeUiState.Empty
            } else {
                val processedList = list.map { subscription ->
                    val result = nextPaymentCalculator(
                        startDateMillis = subscription.paymentDay,
                        cycleType = subscription.paymentCycleType,
                        cycleValue = subscription.paymentCycleValue
                    )
                    SubscriptionInfo(
                        subId = subscription.subId,
                        name = subscription.name,
                        thumbnail = subscription.thumbnail,
                        price = subscription.price,
                        description = subscription.description,
                        nextPaymentDate = result.formattedDate,
                        dDay = result.dDay,
                        rawDate = result.nextPaymentDate,
                        isPaused = subscription.isPaused
                    )
                }

                val (activeList, pausedList) = processedList.partition { !it.isPaused }
                val sortedActiveList = activeList.sortedBy { it.rawDate }
                val nextPaymentInfo = if (sortedActiveList.isNotEmpty()) {
                    val nearestItem = sortedActiveList.first()
                    val groupItems = sortedActiveList.filter { it.rawDate == nearestItem.rawDate }

                    NextPaymentInfo(
                        date = nearestItem.nextPaymentDate ?: "",
                        dDay = nearestItem.dDay ?: "",
                        totalAmount = groupItems.sumOf { it.price ?: 0L },
                        items = groupItems
                    )
                } else {
                    null
                }
                val finalSubscriptionList = sortedActiveList + pausedList
                HomeUiState.Success(
                    subscriptionList = finalSubscriptionList,
                    nextPaymentInfo = nextPaymentInfo
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState.Loading
        )
}
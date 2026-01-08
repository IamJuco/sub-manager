package com.juco.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juco.home.model.SubscriptionInfo
import com.juco.home.util.nextPaymentCalculator
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

    val subscriptionList: StateFlow<List<SubscriptionInfo>> = localRepository.getAllSubscriptions()
        .map { list ->
            list.map { subscription ->
                val (formattedDate, dDay) = nextPaymentCalculator(
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
                    nextPaymentDate = formattedDate,
                    dDay = dDay
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val hasData: StateFlow<Boolean> = subscriptionList
        .map { it.isNotEmpty() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )
}
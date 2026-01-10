package com.juco.local.repository

import com.juco.local.model.Subscription
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun insertSubscription(subscription: Subscription)
    suspend fun updateSubscription(subscription: Subscription)
    suspend fun deleteSubscription(subscription: Subscription)
    fun getAllSubscriptions(): Flow<List<Subscription>>
    suspend fun getSubscriptionById(subId: Long): Subscription
    suspend fun hasSubscriptions(): Boolean
    suspend fun updateNotification(subId: Long, isEnabled: Boolean)
}
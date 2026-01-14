package com.juco.local.repository

import com.juco.local.dao.SubscriptionDao
import com.juco.local.dao.UserDao
import com.juco.local.mapper.toDomain
import com.juco.local.mapper.toEntity
import com.juco.local.model.Subscription
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val subscriptionDao: SubscriptionDao
) : LocalRepository {

    override suspend fun insertSubscription(subscription: Subscription) {
        subscriptionDao.insert(subscription.toEntity())
    }

    override suspend fun updateSubscription(subscription: Subscription) {
        subscriptionDao.update(subscription.toEntity())
    }

    override suspend fun deleteSubscription(subId: Long) {
        subscriptionDao.delete(subId)
    }

    override fun getAllSubscriptions(): Flow<List<Subscription>> {
        return subscriptionDao.getAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getActiveSubscriptions(): List<Subscription> {
        return subscriptionDao.getActiveSubscriptions().map { it.toDomain() }
    }

    override suspend fun getSubscriptionById(subId: Long): Subscription {
        return subscriptionDao.getSubscriptionById(subId).toDomain()
    }

    override suspend fun hasSubscriptions(): Boolean {
        return subscriptionDao.hasSubscriptions()
    }

    override suspend fun updateNotification(subId: Long, isEnabled: Boolean) {
        subscriptionDao.updateNotification(subId, isEnabled)
    }

    override suspend fun updatePause(subId: Long, isPaused: Boolean) {
        subscriptionDao.updatePause(subId, isPaused)
    }
}
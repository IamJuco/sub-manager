package com.juco.local.repository

import com.juco.local.dao.SubscriptionDao
import com.juco.local.dao.UserDao
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val subscriptionDao: SubscriptionDao
) : LocalRepository {
}
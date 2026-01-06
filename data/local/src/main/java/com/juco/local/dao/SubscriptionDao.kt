package com.juco.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.juco.local.entity.SubscriptionEntity

@Dao
interface SubscriptionDao {
    @Insert
    suspend fun insert(subscription: SubscriptionEntity)

    @Query("SELECT * FROM subscriptions")
    fun getAll(): List<SubscriptionEntity>
}
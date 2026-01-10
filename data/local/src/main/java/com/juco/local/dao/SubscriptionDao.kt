package com.juco.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.juco.local.entity.SubscriptionEntity
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscriptionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subscription: SubscriptionEntity)

    @Update
    suspend fun update(subscription: SubscriptionEntity)

    @Delete
    suspend fun delete(subscription: SubscriptionEntity)

    @Query("SELECT * FROM subscriptions ORDER BY subId DESC")
    fun getAll(): Flow<List<SubscriptionEntity>>

    @Query("SELECT * FROM subscriptions WHERE subId = :subId")
    suspend fun getSubscriptionById(subId: Long): SubscriptionEntity

    @Query("SELECT EXISTS(SELECT * FROM subscriptions LIMIT 1)")
    suspend fun hasSubscriptions(): Boolean

    @Query("UPDATE subscriptions SET enableNotification = :isEnabled WHERE subId = :subId")
    suspend fun updateNotification(subId: Long, isEnabled: Boolean)
}
package com.juco.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriptions")
data class SubscriptionEntity(
    @PrimaryKey(autoGenerate = true) val subId: Long = 0,
    val name: String,
    val thumbnail: String,
    val price: Int,
    val paymentDay: Int,
    val description: String? = null
)
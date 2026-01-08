package com.juco.subscription_add.add.model

data class SubscriptionAdd(
    val name: String? = null,
    val price: Long? = null,
    val paymentDay: Long? = null,
    val paymentCycleType: String? = null,
    val paymentCycleValue: Int? = null,
)

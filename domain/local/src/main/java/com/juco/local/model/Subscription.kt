package com.juco.local.model

data class Subscription(
    val subId: Long = 0,
    val name: String,
    val thumbnail: String,
    val price: Long,
    val paymentDay: Long,
    val paymentCycleType: String,
    val paymentCycleValue: Int,
    val description: String? = null
)
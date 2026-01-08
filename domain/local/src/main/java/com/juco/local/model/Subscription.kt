package com.juco.local.model

data class Subscription(
    val subId: Long = 0,
    val name: String,
    val thumbnail: String,
    val price: Int,
    val totalPrice: Int,
    val paymentDay: Int,
    val paymentCycleType: String,
    val paymentCycleValue: Int,
    val description: String? = null
)
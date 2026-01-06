package com.juco.local.model

data class Subscription(
    val subId: Long = 0,
    val name: String,
    val thumbnail: String,
    val price: Int,
    val paymentDay: Int,
    val description: String? = null
)
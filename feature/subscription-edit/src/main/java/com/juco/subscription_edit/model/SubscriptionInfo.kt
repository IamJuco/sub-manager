package com.juco.subscription_edit.model

data class SubscriptionInfo(
    val subId: Long? = null,
    val name: String? = null,
    val thumbnail: String? = null,
    val price: Long? = null,
    val paymentDay: Long? = null,
    val paymentCycleType: String? = null,
    val paymentCycleValue: Int? = null,
    val description: String? = null
)
package com.juco.subscription_detail.model

data class SubscriptionDetailInfo(
    val subId: Long? = null,
    val name: String? = null,
    val thumbnail: String? = null,
    val price: Long? = null,
    val totalAmount: Long? = null,
    val paymentDay: String? = null,
    val nextPaymentDate: String? = null,
    val dDay: String? = null,
    val paymentCycle: String? = null,
    val description: String? = null,
    val enableNotification: Boolean,
    val isPaused: Boolean
)
package com.juco.home.model

data class NextPaymentInfo(
    val date: String? = null,
    val dDay: String? = null,
    val totalAmount: Long? = null,
    val items: List<SubscriptionInfo>? = null
)
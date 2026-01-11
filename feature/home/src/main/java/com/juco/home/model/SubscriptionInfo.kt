package com.juco.home.model

import java.time.LocalDate

data class SubscriptionInfo(
    val subId: Long? = null,
    val name: String? = null,
    val thumbnail: String? = null,
    val price: Long? = null,
    val paymentDay: Long? = null,
    val nextPaymentDate: String? = null,
    val dDay: String? = null,
    val description: String? = null,
    val rawDate: LocalDate? = null,
    val isPaused: Boolean = false
)
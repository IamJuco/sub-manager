package com.juco.subscription_edit.mapper

import com.juco.common.util.PaymentCycle
import com.juco.subscription_edit.model.SubscriptionInfo

fun SubscriptionInfo.toPaymentCycle(): PaymentCycle {
    return if (paymentCycleType != null && paymentCycleValue != null) {
        runCatching {
            PaymentCycle(
                type = com.juco.common.util.PaymentCycleType.valueOf(paymentCycleType ?: ""),
                value = paymentCycleValue ?: 0
            )
        }.getOrDefault(PaymentCycle())
    } else {
        PaymentCycle()
    }
}
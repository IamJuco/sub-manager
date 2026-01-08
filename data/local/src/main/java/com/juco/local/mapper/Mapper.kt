package com.juco.local.mapper

import com.juco.local.entity.SubscriptionEntity
import com.juco.local.model.Subscription

fun SubscriptionEntity.toDomain(): Subscription {
    return Subscription(
        subId = subId,
        name = name,
        thumbnail = thumbnail,
        price = price,
        paymentDay = paymentDay,
        paymentCycleType = paymentCycleType,
        paymentCycleValue = paymentCycleValue,
        description = description,
        enableNotification = enableNotification
    )
}

fun Subscription.toEntity(): SubscriptionEntity {
    return SubscriptionEntity(
        subId = subId,
        name = name,
        thumbnail = thumbnail,
        price = price,
        paymentDay = paymentDay,
        paymentCycleType = paymentCycleType,
        paymentCycleValue = paymentCycleValue,
        description = description,
        enableNotification = enableNotification
    )
}
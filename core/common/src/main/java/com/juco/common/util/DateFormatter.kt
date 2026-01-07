package com.juco.common.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val dateFormatter = DateTimeFormatter.ofPattern("yyyy. MM. dd")

// UnixTime -> String
fun formatDate(millis: Long): String {
    return Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .format(dateFormatter)
}

fun calculateNextPaymentDate(startDateMillis: Long, type: PaymentCycleType, value: Int): Long {
    if (type == PaymentCycleType.NONE || value <= 0) return startDateMillis

    val startDate = Instant.ofEpochMilli(startDateMillis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    val nextDate = when (type) {
        PaymentCycleType.DAY -> startDate.plusDays(value.toLong())
        PaymentCycleType.MONTH -> startDate.plusMonths(value.toLong())
        PaymentCycleType.YEAR -> startDate.plusYears(value.toLong())
        else -> startDate
    }

    return nextDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

data class PaymentCycle(
    val type: PaymentCycleType = PaymentCycleType.NONE,
    val value: Int = 0
) {
    fun toDisplayText(): String {
        return when (type) {
            PaymentCycleType.DAY -> "${value}일 마다"
            PaymentCycleType.MONTH -> "${value}개월 마다"
            PaymentCycleType.YEAR -> "${value}년 마다"
            PaymentCycleType.NONE -> ""
        }
    }
}
package com.juco.common.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private val dateFormatter = DateTimeFormatter.ofPattern("yy년 M월 d일 (E)", Locale.KOREA)

fun formatDate(millis: Long): String {
    return Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .format(dateFormatter)
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
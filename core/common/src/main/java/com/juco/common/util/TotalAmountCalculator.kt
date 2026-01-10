package com.juco.common.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

suspend fun totalAmountCalculator(
    startDateMillis: Long,
    price: Long,
    cycleType: String,
    cycleValue: Int
): Long = withContext(Dispatchers.Default) {
    val startDate = Instant.ofEpochMilli(startDateMillis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    val today = LocalDate.now()

    if (startDate.isAfter(today)) {
        return@withContext 0L
    }

    var count = 0
    var nextDate = startDate

    while (!nextDate.isAfter(today)) {
        count++

        nextDate = when (cycleType) {
            "DAY" -> nextDate.plusDays(cycleValue.toLong())
            "MONTH" -> nextDate.plusMonths(cycleValue.toLong())
            "YEAR" -> nextDate.plusYears(cycleValue.toLong())
            else -> nextDate.plusMonths(1)
        }
    }

    return@withContext count * price
}
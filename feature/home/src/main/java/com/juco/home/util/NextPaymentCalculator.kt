package com.juco.home.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

private val dateFormatter = DateTimeFormatter.ofPattern("yy년 M월 d일 (E)", Locale.KOREA)

/**
 * 다음 결제일과 D-Day 계산
 */
suspend fun nextPaymentCalculator(
    startDateMillis: Long,
    cycleType: String,
    cycleValue: Int
): Pair<String, String> = withContext(Dispatchers.Default) {
    val startDate = Instant.ofEpochMilli(startDateMillis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    val today = LocalDate.now()
    var nextDate = startDate

    if (nextDate.isBefore(today)) {
        while (!nextDate.isAfter(today.minusDays(1))) {
            nextDate = when (cycleType) {
                "DAY" -> nextDate.plusDays(cycleValue.toLong())
                "MONTH" -> nextDate.plusMonths(cycleValue.toLong())
                "YEAR" -> nextDate.plusYears(cycleValue.toLong())
                else -> nextDate.plusMonths(1)
            }
        }
    }

    val formattedDate = nextDate.format(dateFormatter)

    val daysDiff = ChronoUnit.DAYS.between(today, nextDate)
    val dDayText = when {
        daysDiff == 0L -> "D-Day"
        daysDiff > 0L -> "D-$daysDiff"
        else -> "D+$daysDiff"
    }

    Pair(formattedDate, dDayText)
}
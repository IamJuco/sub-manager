package com.juco.home.model

import java.time.LocalDate

data class PaymentCalculationResult(
    val formattedDate: String,
    val dDay: String,
    val rawDate: LocalDate
)
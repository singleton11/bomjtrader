package com.bomjtrader.server.domain

import java.time.LocalDateTime

data class Bar(
    val close: Double,
    val high: Double,
    val low: Double,
    val number: Int,
    val open: Double,
    val time: LocalDateTime,
    val volume: Double,
    val volumeWeighted: Double
)

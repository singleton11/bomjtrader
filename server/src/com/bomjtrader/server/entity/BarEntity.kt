package com.bomjtrader.server.entity

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class BarEntity(
  @Id
  val id: Long,
  val close: Double,
  val high: Double,
  val low: Double,
  val number: Int,
  val open: Double,
  val time: LocalDateTime,
  val volume: Double,
  val volumeWeighted: Double
)

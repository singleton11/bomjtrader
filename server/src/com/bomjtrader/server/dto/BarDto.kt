package com.bomjtrader.server.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class BarDto(
  @get:JsonProperty("c")
  val close: Double,
  @get:JsonProperty("h")
  val high: Double,
  @get:JsonProperty("l")
  val low: Double,
  @get:JsonProperty("n")
  val number: Int,
  @get:JsonProperty("o")
  val open: Double,
  @get:JsonProperty("t")
  val time: LocalDateTime,
  @get:JsonProperty("v")
  val volume: Double,
  @get:JsonProperty("vw")
  val volumeWeighted: Double
)

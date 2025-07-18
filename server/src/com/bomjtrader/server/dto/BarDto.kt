package com.bomjtrader.server.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class BarDto(
  @field:JsonProperty("c")
  val close: Double,
  @field:JsonProperty("h")
  val high: Double,
  @field:JsonProperty("l")
  val low: Double,
  @field:JsonProperty("n")
  val number: Int,
  @field:JsonProperty("o")
  val open: Double,
  @field:JsonProperty("t")
  val time: LocalDateTime,
  @field:JsonProperty("v")
  val volume: Double,
  @field:JsonProperty("vw")
  val volumeWeighted: Double
)

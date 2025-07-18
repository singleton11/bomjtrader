package com.bomjtrader.server.mapper

import com.bomjtrader.server.domain.Bar
import com.bomjtrader.server.dto.BarDto
import org.springframework.stereotype.Component

@Component
class BarMapper {
  fun mapBack(bar: Bar): BarDto = BarDto(
    bar.close,
    bar.high,
    bar.low,
    bar.number,
    bar.open,
    bar.time,
    bar.volume,
    bar.volumeWeighted,
  )

  fun mapBack(bars: List<Bar>): List<BarDto> = bars.map(::mapBack)

  fun map(barDto: BarDto): Bar = Bar(
    barDto.close,
    barDto.high,
    barDto.low,
    barDto.number,
    barDto.open,
    barDto.time,
    barDto.volume,
    barDto.volumeWeighted,
  )

  fun map(bars: List<BarDto>): List<Bar> = bars.map(::map)
}

package com.bomjtrader.server.repository

import com.bomjtrader.server.dto.AssetDto
import org.springframework.web.service.annotation.GetExchange

interface AlpacaTradingRepository {
  @GetExchange("/assets")
  suspend fun assets(): List<AssetDto>
}

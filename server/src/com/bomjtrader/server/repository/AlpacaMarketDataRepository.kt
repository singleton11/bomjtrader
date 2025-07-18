package com.bomjtrader.server.repository

import com.bomjtrader.server.dto.BarsResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface AlpacaMarketDataRepository {
  @GetExchange("/stocks/{symbol}/bars")
  suspend fun historicalBars(@PathVariable symbol: String = "AAPL", @RequestParam timeframe: String = "1Min"): BarsResponse
}

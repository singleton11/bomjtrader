package com.bomjtrader.server.service

import com.bomjtrader.server.domain.Bar
import com.bomjtrader.server.mapper.BarMapper
import com.bomjtrader.server.repository.AlpacaMarketDataRepository
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Service
class MarketDataService(webClient: WebClient, private val barMapper: BarMapper) {
  private val repository: AlpacaMarketDataRepository

  init {
    val adapter = WebClientAdapter.create(webClient)
    val factory = HttpServiceProxyFactory.builderFor(adapter).build()
    repository = factory.createClient(AlpacaMarketDataRepository::class.java)
  }

  suspend fun historicalBars(symbol: String = "AAPL"): List<Bar> = barMapper.map(repository.historicalBars(symbol).bars)
}

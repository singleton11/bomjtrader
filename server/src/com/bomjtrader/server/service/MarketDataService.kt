package com.bomjtrader.server.service

import com.bomjtrader.server.domain.Asset
import com.bomjtrader.server.domain.Bar
import com.bomjtrader.server.mapper.AssetMapper
import com.bomjtrader.server.mapper.BarMapper
import com.bomjtrader.server.repository.AlpacaMarketDataRepository
import com.bomjtrader.server.repository.AlpacaTradingRepository
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Service
class MarketDataService(
  marketDataWebClient: WebClient,
  tradingWebClient: WebClient,
  private val barMapper: BarMapper,
  private val assetMapper: AssetMapper,
) {
  private val marketDataRepository: AlpacaMarketDataRepository
  private val tradingRepository: AlpacaTradingRepository

  init {
    val marketDataAdapter = WebClientAdapter.create(marketDataWebClient)
    val marketDataFactory = HttpServiceProxyFactory.builderFor(marketDataAdapter).build()
    marketDataRepository = marketDataFactory.createClient(AlpacaMarketDataRepository::class.java)

    val tradingAdapter = WebClientAdapter.create(tradingWebClient)
    val tradingFactory = HttpServiceProxyFactory.builderFor(tradingAdapter).build()
    tradingRepository = tradingFactory.createClient(AlpacaTradingRepository::class.java)
  }

  suspend fun historicalBars(symbol: String = "AAPL"): List<Bar> =
    barMapper.map(marketDataRepository.historicalBars(symbol).bars)

  suspend fun assets(): List<Asset> = assetMapper.map(tradingRepository.assets())
}

package com.bomjtrader.server.config

import com.bomjtrader.server.filter.AlpacaKeyProviderFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.unit.DataSize
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebConfig(private val alpacaKeyProviderFilter: AlpacaKeyProviderFilter) {

  @Bean
  fun marketDataWebClient(@Value("\${alpaca.marketData.url}") baseUrl: String): WebClient = WebClient.builder()
    .baseUrl(baseUrl)
    .filter(alpacaKeyProviderFilter)
    .build()

  @Bean
  fun tradingWebClient(@Value("\${alpaca.trading.url}") baseUrl: String): WebClient {
    val strategies = ExchangeStrategies.builder()
      .codecs { configurer ->
        configurer.defaultCodecs()
          .maxInMemorySize(DataSize.ofMegabytes(50).toBytes().toInt())
      }
      .build()
    return WebClient.builder()
      .baseUrl(baseUrl)
      .filter(alpacaKeyProviderFilter)
      .exchangeStrategies(strategies)
      .build()
  }
}

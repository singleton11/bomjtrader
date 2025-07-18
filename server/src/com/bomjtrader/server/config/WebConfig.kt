package com.bomjtrader.server.config

import com.bomjtrader.server.filter.AlpacaKeyProviderFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebConfig(private val alpacaKeyProviderFilter: AlpacaKeyProviderFilter) {

  @Bean
  fun marketDataWebClient(@Value("\${alpaca.marketData.url}") baseUrl: String): WebClient = WebClient.builder()
    .baseUrl(baseUrl)
    .filter(alpacaKeyProviderFilter)
    .build()
}

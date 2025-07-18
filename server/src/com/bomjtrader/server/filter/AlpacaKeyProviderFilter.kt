package com.bomjtrader.server.filter

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.ExchangeFunction
import reactor.core.publisher.Mono

@Component
class AlpacaKeyProviderFilter(
    @param:Value("\${alpaca.key.id:alpaca}") private val keyId: String,
    @param:Value("\${alpaca.key.secret:alpaca}") private val keySecret: String
) : ExchangeFilterFunction {

  override fun filter(request: ClientRequest, next: ExchangeFunction): Mono<ClientResponse> {
    // Create a new request with the Authorization header
    val filteredRequest = ClientRequest.from(request)
      .header("APCA-API-KEY-ID", keyId)
      .header("APCA-API-SECRET-KEY", keySecret)
      .build()

    // Continue with the filter chain
    return next.exchange(filteredRequest)
  }
}

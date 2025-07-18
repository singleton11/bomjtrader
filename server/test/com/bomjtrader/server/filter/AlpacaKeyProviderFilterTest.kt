package com.bomjtrader.server.filter

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.web.reactive.function.client.WebClient
import org.wiremock.spring.EnableWireMock

@SpringBootTest
@EnableWireMock
@TestPropertySource(
  properties = [
    "alpaca.marketData.url=\${wiremock.server.baseUrl}",
    "alpaca.key.id=test",
    "alpaca.key.secret=test"
  ]
)
class AlpacaKeyProviderFilterTest {

  @Autowired
  private lateinit var webClient: WebClient

  @Test
  fun `should add Alpaca API key headers to requests`() {
    stubFor(
      WireMock.get(
        WireMock.anyUrl()
      )
        .willReturn(
          WireMock.aResponse()
            .withStatus(200)
            .withBody("{}")
        )
    )

    // Make a request using the WebClient
    webClient.get()
      .uri("/v2/stocks/AAPL/bars")
      .retrieve()
      .bodyToMono(String::class.java)
      .block()

    // Verify that the request was made with the expected headers
    verify(
      WireMock.getRequestedFor(
        WireMock.anyUrl()
      )
        .withHeader("APCA-API-KEY-ID", equalTo("test"))
        .withHeader("APCA-API-SECRET-KEY", equalTo("test"))
    )
  }
}

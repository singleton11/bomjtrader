package com.bomjtrader.server.crypto

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.test.context.TestPropertySource
import java.net.URI

@SpringBootTest
@TestPropertySource(
  properties = [
    "coinbase.key.name=test-key",
    "coinbase.key.value=-----BEGIN EC PRIVATE KEY-----\\nMHcCAQEEIGjhTmzCJiUk3QoxZOJJgzxUE6CuBPZFvNwfwzlq+O+3oAoGCCqGSM49\\nAwEHoUQDQgAEdgn/cuH7oA9UDS7gnY7iAjv3h0u2gKNcyIHK1K5LxBkHzdPFlz2G\\n2gT0HpykKA3531VcXthuMhf3Rszqx+U8CQ==\\n-----END EC PRIVATE KEY-----\\n"
  ]
)

class JwtProviderTest {
  @Autowired
  private lateinit var jwtProvider: JwtProvider

  @Test
  fun sign() {
    val jwt = jwtProvider.sign(HttpMethod.GET, URI.create("https://api.binance.com/api/v3/ticker/price"))
    assertNotNull(jwt)
  }
}

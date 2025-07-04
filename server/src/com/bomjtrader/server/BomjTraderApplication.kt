package com.bomjtrader.server

import com.bomjtrader.server.crypto.JwtProvider
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpMethod
import java.net.URI

@SpringBootApplication
class BomjTraderApplication(private val jwtProvider: JwtProvider) {
  @PostConstruct
  fun init() {
    println(jwtProvider.sign(HttpMethod.GET, URI.create("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT")))
  }
}

fun main(args: Array<String>) {
    runApplication<BomjTraderApplication>()
}

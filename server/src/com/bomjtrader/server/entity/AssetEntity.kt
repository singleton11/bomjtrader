package com.bomjtrader.server.entity

import org.springframework.data.annotation.Id

data class AssetEntity(
  @Id
  val id: String,
  val assetClass: Class,
  val exchange: Exchange,
  val symbol: String,
  val name: String,
  val status: Status,
  val tradable: Boolean,
  val marginable: Boolean,
  val maintenanceMarginRequirement: Double,
  val marginRequirementLong: String,
  val marginRequirementShort: String,
  val shortable: Boolean,
  val easyToBorrow: Boolean,
  val fractionable: Boolean,
  val attributes: List<String>
) {
  enum class Class {
    UsEquity,
    UsMutualFund,
    UsStock,
    UsOption,
    Crypto
  }

  enum class Status {
    Active, Inactive, PendingDeactivation
  }

  enum class Exchange {
    Amex, Arca, Bats, Nyse, Nasdaq, NyseArca, Otc, Crypto
  }
}

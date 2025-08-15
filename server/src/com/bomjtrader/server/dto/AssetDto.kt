package com.bomjtrader.server.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue

/**
 * {
 *     "id": "f895eff8-982f-4521-8af7-95d03847a809",
 *     "class": "us_equity",
 *     "exchange": "OTC",
 *     "symbol": "ODRS",
 *     "name": "Outdoor Specialty Prods Inc Common Stock",
 *     "status": "inactive",
 *     "tradable": false,
 *     "marginable": false,
 *     "maintenance_margin_requirement": 100,
 *     "margin_requirement_long": "100",
 *     "margin_requirement_short": "100",
 *     "shortable": false,
 *     "easy_to_borrow": false,
 *     "fractionable": false,
 *     "attributes": []
 *   },
 */
//
data class AssetDto(
  val id: String,
  @get:JsonProperty("class") val assetClass: AssetClass,
  val exchange: Exchange,
  val symbol: String,
  val name: String,
  val status: AssetStatus,
  val tradable: Boolean,
  val marginable: Boolean,
  @get:JsonProperty("maintenance_margin_requirement") val maintenanceMarginRequirement: Double,
  @get:JsonProperty("margin_requirement_long") val marginRequirementLong: String,
  @get:JsonProperty("margin_requirement_short") val marginRequirementShort: String,
  val shortable: Boolean,
  @get:JsonProperty("easy_to_borrow") val easyToBorrow: Boolean,
  val fractionable: Boolean,
  val attributes: List<String>
) {
  enum class AssetClass(private val value: String) {
    US_EQUITY("us_equity"),
    US_MUTUAL_FUND("us_mutual_fund"),
    US_STOCK("us_stock"),
    US_OPTION("us_option"),
    CRYPTO("crypto");

    @JsonValue
    fun getValue(): String = value
  }

  enum class Exchange {
    AMEX, ARCA, BATS, NYSE, NASDAQ, NYSEARCA, OTC, CRYPTO;
  }

  enum class AssetStatus(private val value: String) {
    ACTIVE("active"),
    INACTIVE("inactive"),
    PENDING_DEACTIVATION("pending_deactivation");

    @JsonValue
    fun getValue(): String = value
  }
}

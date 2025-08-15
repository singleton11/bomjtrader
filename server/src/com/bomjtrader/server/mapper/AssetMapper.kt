package com.bomjtrader.server.mapper

import com.bomjtrader.server.domain.Asset
import com.bomjtrader.server.dto.AssetDto
import org.springframework.stereotype.Component

@Component
class AssetMapper {
  fun map(assetDto: AssetDto): Asset = Asset(
    id = assetDto.id,
    `class` = assetDto.assetClass.map(),
    exchange = assetDto.exchange.map(),
    symbol = assetDto.symbol,
    name = assetDto.name,
    status = assetDto.status.map(),
    tradeable = assetDto.tradable,
    marginable = assetDto.marginable,
    maintenanceMarginRequirement = assetDto.maintenanceMarginRequirement,
    marginRequirementLong = assetDto.marginRequirementLong,
    marginRequirementShort = assetDto.marginRequirementShort,
    shortable = assetDto.shortable,
    easyToBorrow = assetDto.easyToBorrow,
    fractionable = assetDto.fractionable,
    attributes = assetDto.attributes
  )

  fun map(asset: Asset): AssetDto = AssetDto(
    id = asset.id,
    assetClass = asset.`class`.map(),
    exchange = asset.exchange.map(),
    symbol = asset.symbol,
    name = asset.name,
    status = asset.status.map(),
    tradable = asset.tradeable,
    marginable = asset.marginable,
    maintenanceMarginRequirement = asset.maintenanceMarginRequirement,
    marginRequirementLong = asset.marginRequirementLong,
    marginRequirementShort = asset.marginRequirementShort,
    shortable = asset.shortable,
    easyToBorrow = asset.easyToBorrow,
    fractionable = asset.fractionable,
    attributes = asset.attributes
  )

  fun map(assets: List<AssetDto>): List<Asset> = assets.map { map(it) }

  private fun AssetDto.AssetClass.map(): Asset.Class = when (this) {
    AssetDto.AssetClass.US_EQUITY -> Asset.Class.UsEquity
    AssetDto.AssetClass.US_MUTUAL_FUND -> Asset.Class.UsMutualFund
    AssetDto.AssetClass.US_STOCK -> Asset.Class.UsStock
    AssetDto.AssetClass.US_OPTION -> Asset.Class.UsOption
    AssetDto.AssetClass.CRYPTO -> Asset.Class.Crypto
  }

  private fun Asset.Class.map(): AssetDto.AssetClass = when (this) {
    Asset.Class.UsEquity -> AssetDto.AssetClass.US_EQUITY
    Asset.Class.UsMutualFund -> AssetDto.AssetClass.US_MUTUAL_FUND
    Asset.Class.UsStock -> AssetDto.AssetClass.US_STOCK
    Asset.Class.UsOption -> AssetDto.AssetClass.US_OPTION
    Asset.Class.Crypto -> AssetDto.AssetClass.CRYPTO
  }

  private fun AssetDto.Exchange.map(): Asset.Exchange = when (this) {
    AssetDto.Exchange.AMEX -> Asset.Exchange.Amex
    AssetDto.Exchange.ARCA -> Asset.Exchange.Arca
    AssetDto.Exchange.BATS -> Asset.Exchange.Bats
    AssetDto.Exchange.NYSE -> Asset.Exchange.Nyse
    AssetDto.Exchange.NASDAQ -> Asset.Exchange.Nasdaq
    AssetDto.Exchange.NYSEARCA -> Asset.Exchange.NyseArca
    AssetDto.Exchange.OTC -> Asset.Exchange.Otc
    AssetDto.Exchange.CRYPTO -> Asset.Exchange.Crypto
  }

  private fun Asset.Exchange.map(): AssetDto.Exchange = when (this) {
    Asset.Exchange.Amex -> AssetDto.Exchange.AMEX
    Asset.Exchange.Arca -> AssetDto.Exchange.ARCA
    Asset.Exchange.Bats -> AssetDto.Exchange.BATS
    Asset.Exchange.Nyse -> AssetDto.Exchange.NYSE
    Asset.Exchange.Nasdaq -> AssetDto.Exchange.NASDAQ
    Asset.Exchange.NyseArca -> AssetDto.Exchange.NYSEARCA
    Asset.Exchange.Otc -> AssetDto.Exchange.OTC
    Asset.Exchange.Crypto -> AssetDto.Exchange.CRYPTO
  }

  private fun AssetDto.AssetStatus.map(): Asset.Status = when (this) {
    AssetDto.AssetStatus.ACTIVE -> Asset.Status.Active
    AssetDto.AssetStatus.INACTIVE -> Asset.Status.Inactive
    AssetDto.AssetStatus.PENDING_DEACTIVATION -> Asset.Status.PendingDeactivation
  }

  private fun Asset.Status.map(): AssetDto.AssetStatus = when (this) {
    Asset.Status.Active -> AssetDto.AssetStatus.ACTIVE
    Asset.Status.Inactive -> AssetDto.AssetStatus.INACTIVE
    Asset.Status.PendingDeactivation -> AssetDto.AssetStatus.PENDING_DEACTIVATION
  }
}

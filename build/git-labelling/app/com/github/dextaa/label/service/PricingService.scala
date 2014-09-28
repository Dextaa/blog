package com.github.dextaa.label.service

import com.github.dextaa.label.service.api.types.{CurrencyCode, CurrencyValue, PriceData, StockSymbol}

/**
 * Implementation of #api.PricingService.
 */
class PricingService extends api.PricingService {

  private val usd = CurrencyCode("USD")

  /**
   * @inheritdoc
   */
  override def currentPricing(symbol: StockSymbol): Option[PriceData] = symbol.key match {
    case "ORCL" => {
      Some (
        PriceData(symbol,
        price = CurrencyValue(usd, BigDecimal(41.53)),
        dayPriceLow = CurrencyValue(usd, BigDecimal(41.24)),
        dayPriceHigh = CurrencyValue(usd, BigDecimal(41.73)))
      )
    }
    case _ => None
  }

}

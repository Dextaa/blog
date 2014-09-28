package com.github.dextaa.label.service.api

import com.github.dextaa.label.service.api.types.{PriceData, StockSymbol}

/**
 * Stock price data service descriptor.
 */
trait PricingService {

  /**
   * Provides current price information for the subject #symbol.
   * @param symbol the stock symbol for which pricing data is required.
   * @return optional pricing data.
   */
  def currentPricing(symbol: StockSymbol): Option[PriceData]

}

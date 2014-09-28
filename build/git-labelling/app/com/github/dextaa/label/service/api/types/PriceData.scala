package com.github.dextaa.label.service.api.types

import org.joda.time.DateTime

/**
 * Snapshot of pricing data for a given stock.
 * @param symbol the stock symbol.
 * @param price the current stock price for #symbol.
 * @param dayPriceLow the day's low stock price for #symbol.
 * @param dayPriceHigh the day's high stock price for #symbol.
 */
case class PriceData(symbol: StockSymbol, price: CurrencyValue,
                     dayPriceLow: CurrencyValue, dayPriceHigh: CurrencyValue) {
  val when = DateTime.now
}
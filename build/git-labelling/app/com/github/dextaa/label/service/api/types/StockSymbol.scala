package com.github.dextaa.label.service.api.types

import scala.language.implicitConversions

/**
 * Descriptive type wrapper for a stock symbol e.g. ORCL.
 * @param key the stock symbol.
 */
case class StockSymbol(key: String)

object StockSymbol {

  implicit def asStockSymbol(symbol: String) = new StockSymbol(symbol)

}
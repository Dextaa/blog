package com.github.dextaa.label.util

import com.github.dextaa.label.service.api.types.{PriceData, CurrencyValue, StockSymbol, CurrencyCode}
import play.api.libs.json._

object PricingConversions {

  implicit val currencyCodeConversion = new Reads[CurrencyCode] {
    override def reads(json: JsValue): JsResult[CurrencyCode] = JsSuccess(CurrencyCode(json.as[String]))
  }

  implicit val stockSymbolConversion = new Reads[StockSymbol] {
    override def reads(json: JsValue): JsResult[StockSymbol] = JsSuccess(StockSymbol(json.as[String]))
  }

  implicit val currencyValueConversion = Json.reads[CurrencyValue]
  implicit val priceDataConversion = Json.reads[PriceData]

}

package controllers.conversion

import com.github.dextaa.label.service.api.types.{PriceData, StockSymbol, CurrencyValue, CurrencyCode}
import play.api.libs.json.{JsString, JsValue, Writes, Json}

/**
 * Implicits for converting price types to json.
 */
object PricingConversions {

  implicit val currencyCodeConversion = new Writes[CurrencyCode] {
    override def writes(o: CurrencyCode): JsValue = new JsString(o.key)
  }

  implicit val stockSymbolConversion = new Writes[StockSymbol] {
    override def writes(o: StockSymbol): JsValue = new JsString(o.key)
  }

  implicit val currencyValueConversion = Json.writes[CurrencyValue]
  implicit val priceDataConversion = Json.writes[PriceData]

}

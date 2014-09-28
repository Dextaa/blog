package controllers

import com.github.dextaa.label.service.api.types.{StockSymbol, PriceData}
import com.github.dextaa.label.service.{PricingService, api}
import controllers.conversion.PricingConversions._
import play.api.libs.json.Json
import play.api.mvc._

/**
 * The sole 'business' controller which provides stock pricing data.
 */
object Pricing extends Controller {

  private val pricingService: api.PricingService = new PricingService

  /**
   * Provides current price information for the subject #symbol.
   * @param symbol the stock symbol for which pricing data is required.
   * @return 'OK' (200) with a json body describing the current price (and associated data) OR
   *         'Bad Request' (400) with a json error body if no matching price data could be found.
   */
  def current(symbol: String) = Action {
    pricingService.currentPricing(symbol).fold(noPriceData(symbol))(pricing)
  }

  private def noPriceData(implicit symbol: StockSymbol): Result =
    BadRequest(Json.obj("error" -> s"No data found for symbol ${symbol.key}"))

  private def pricing(data: PriceData): Result = Ok(Json.toJson(data))

}
package com.github.dextaa.label

import com.github.dextaa.label.service.api.types.{CurrencyValue, CurrencyCode, StockSymbol, PriceData}
import env.GlobalServer
import org.scalatest.{FeatureSpecLike, GivenWhenThen, Matchers}
import play.api.libs.json.Json
import play.api.libs.ws.WS
import scala.concurrent.Await
import scala.concurrent.duration._
import util.PricingConversions._
import play.api.Play.current

/**
 * This is just to provide confidence that the base app is working properly. As it's the build process
 * rather than the app itself that's of interest, this test can be ignored.
 */
class PricingFeatureSpec extends FeatureSpecLike with GlobalServer with GivenWhenThen with Matchers {

  private val maxWait = Duration(1, SECONDS)

  feature("the system should allow users to access stock price data") {

    scenario("a user can query stock price by providing a stock symbol") {

      val response = Await.result(WS.url(s"http://localhost:$listenPort/price/ORCL").get(), maxWait)
      response.status should be (200)
      Json.fromJson[PriceData](Json.parse(response.body)).fold (
        err => fail("Error parsing pricing data response"),
        pd => verifyPriceData(pd)
      )
    }

    scenario("a user will receive a 'bad request' response when an invalid stock symbol is provided") {
      val response = Await.result(WS.url(s"http://localhost:$listenPort/price/SUN").get(), maxWait)
      response.status should be (400)
      val body = Json.parse(response.body)
      (body \ "error").as[String] should be ("No data found for symbol SUN")
    }

  }

  private def verifyPriceData(pd: PriceData) = {
    val usd = CurrencyCode("USD")
    pd.symbol should equal(StockSymbol("ORCL"))
    pd.price should equal(CurrencyValue(usd, BigDecimal(41.53)))
    pd.dayPriceLow should equal(CurrencyValue(usd, BigDecimal(41.24)))
    pd.dayPriceHigh should equal(CurrencyValue(usd, BigDecimal(41.73)))
  }

}

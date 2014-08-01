package com.github.dextaa.ids.service

import com.github.dextaa.ids.model.api.types.{CompanyId, TraderId}
import com.github.dextaa.ids.model.slick.Database
import com.github.dextaa.ids.model.slick.generated.Schema._
import com.github.dextaa.ids.model.slick.mapping.MySqlIdentity._
import com.github.dextaa.ids.service.api.types.BuyOrder
import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}

import scala.slick.driver.MySQLDriver.simple._

class MarketTest extends FlatSpec with GivenWhenThen with Matchers {

  val companyId = new CompanyId(100)
  val traderId = new TraderId(200)

  "The Market service" should "support a buy operation using the expressive id types" in {

    val marketService: api.Market = new MarketService

    Given("an existing company and trader")
    Database.withSession{ implicit session =>

      Company.delete
      Trader.delete
      Buy.delete

      Company += CompanyRow(companyId, "Acme Trading Co")
      Trader += TraderRow(traderId, "trader@acme-trading.com")
    }

    When("several buy orders are placed by the trader")

    val buyOrders = Seq(BuyOrder(BigDecimal(100.07), "trade1"),
                        BuyOrder(BigDecimal(200.47), "trade2"),
                        BuyOrder(BigDecimal(120.00), "trade3"),
                        BuyOrder(BigDecimal(458.01), "trade4"))

    buyOrders.foreach(marketService.buy(companyId, traderId, _))

    Then("it should be possible to execute queries which verify that the buy was placed")

    val previousBuys = Database.withSession{ implicit session =>
      //This query syntax requires that the MySqlIdentity implicits are in scope.
      Buy.filter(b => b.companyId === companyId && b.traderId === traderId).list
    }

    previousBuys should have length (buyOrders.length)
    buyOrders.foreach(b => previousBuys.exists(p => p.reference == b.reference) should be (true))

  }


}

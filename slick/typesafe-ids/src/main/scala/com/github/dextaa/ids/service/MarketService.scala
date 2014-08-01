package com.github.dextaa.ids.service

import api.Market
import com.github.dextaa.ids.model.api.types.{CompanyId, TraderId}
import com.github.dextaa.ids.model.slick.Database
import com.github.dextaa.ids.model.slick.generated.Schema._
import scala.slick.driver.MySQLDriver.simple._

class MarketService extends Market {

  override def recordBuy(companyId: CompanyId, traderId: TraderId, value: BigDecimal, reference: String): Boolean = {

    Database.withSession{ implicit session =>
      val expectedRowCount = 1
      (Buy += BuyRow(companyId, traderId, value, reference)) == expectedRowCount
    }
  }

  override def debitAccount(traderId: TraderId, companyId: CompanyId, value: BigDecimal): Unit = {
    //Other than the signature, this adds nothing to the example so is not implemented.
  }

}

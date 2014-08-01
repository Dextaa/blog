package com.github.dextaa.ids.service.api

import types._
import com.github.dextaa.ids.model.api.types.{TraderId, CompanyId}

trait Market {

  def buy(companyId: CompanyId, traderId: TraderId, buy: BuyOrder) {
    recordBuy(companyId, traderId, buy.value, buy.reference)
    debitAccount(traderId, companyId, buy.value)
  }

  def recordBuy(companyId: CompanyId, traderId: TraderId,
                   value: BigDecimal, reference: String): Boolean

  def debitAccount(traderId: TraderId, companyId: CompanyId,
                value: BigDecimal): Unit
}

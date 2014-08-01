package com.github.dextaa.ids.model.slick.mapping

import com.github.dextaa.ids.model.api.types.{CompanyId, TraderId}
import scala.slick.driver.{MySQLDriver, JdbcDriver}
import scala.slick.jdbc.{PositionedResult, PositionedParameters}

class DatabaseIdentity(val driver: JdbcDriver) {

  implicit object TraderIdTypeMapper extends driver.DriverJdbcType[TraderId] {
    def zero = new TraderId(0L)
    def sqlType = java.sql.Types.BIGINT
    def setValue(id: TraderId, params: PositionedParameters) = params.setLong(id.key)
    def setOption(id: Option[TraderId], params: PositionedParameters) = params.setLongOption(id.map{_.key})
    def nextValue(result: PositionedResult) = new TraderId(result.nextLong())
    def updateValue(id: TraderId, result: PositionedResult) = result.updateLong(id.key)
    override def valueToSQLLiteral(value: TraderId) = value.key.toString
  }

  implicit object CompanyIdTypeMapper extends driver.DriverJdbcType[CompanyId] {
    def zero = new CompanyId(0L)
    def sqlType = java.sql.Types.BIGINT
    def setValue(id: CompanyId, params: PositionedParameters) = params.setLong(id.key)
    def setOption(id: Option[CompanyId], params: PositionedParameters) = params.setLongOption(id.map{_.key})
    def nextValue(result: PositionedResult) = new CompanyId(result.nextLong())
    def updateValue(id: CompanyId, result: PositionedResult) = result.updateLong(id.key)
    override def valueToSQLLiteral(value: CompanyId) = value.key.toString
  }

}

object MySqlIdentity extends DatabaseIdentity(MySQLDriver)

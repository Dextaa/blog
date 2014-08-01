package com.github.dextaa.ids.model.slick.generated
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Schema extends {
  val profile = scala.slick.driver.MySQLDriver
} with Schema

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Schema {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import com.github.dextaa.ids.model.slick.mapping.MySqlIdentity._
  import com.github.dextaa.ids.model.api.types._
  import scala.slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import scala.slick.jdbc.{GetResult => GR}
  
  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = Buy.ddl ++ Company.ddl ++ Trader.ddl
  
  /** Entity class storing rows of table Buy
   *  @param companyId Database column company_id 
   *  @param traderId Database column trader_id 
   *  @param value Database column value 
   *  @param reference Database column reference  */
  case class BuyRow(companyId: CompanyId, traderId: TraderId, value: scala.math.BigDecimal, reference: String)
  /** GetResult implicit for fetching BuyRow objects using plain SQL queries */
  implicit def GetResultBuyRow(implicit e0: GR[CompanyId], e1: GR[TraderId], e2: GR[scala.math.BigDecimal], e3: GR[String]): GR[BuyRow] = GR{
    prs => import prs._
    BuyRow.tupled((<<[CompanyId], <<[TraderId], <<[scala.math.BigDecimal], <<[String]))
  }
  /** Table description of table buy. Objects of this class serve as prototypes for rows in queries. */
  class Buy(tag: Tag) extends Table[BuyRow](tag, "buy") {
    def * = (companyId, traderId, value, reference) <> (BuyRow.tupled, BuyRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (companyId.?, traderId.?, value.?, reference.?).shaped.<>({r=>import r._; _1.map(_=> BuyRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column company_id  */
    val companyId: Column[CompanyId] = column[CompanyId]("company_id")
    /** Database column trader_id  */
    val traderId: Column[TraderId] = column[TraderId]("trader_id")
    /** Database column value  */
    val value: Column[scala.math.BigDecimal] = column[scala.math.BigDecimal]("value")
    /** Database column reference  */
    val reference: Column[String] = column[String]("reference")
  }
  /** Collection-like TableQuery object for table Buy */
  lazy val Buy = new TableQuery(tag => new Buy(tag))
  
  /** Entity class storing rows of table Company
   *  @param companyId Database column company_id 
   *  @param name Database column name  */
  case class CompanyRow(companyId: CompanyId, name: String)
  /** GetResult implicit for fetching CompanyRow objects using plain SQL queries */
  implicit def GetResultCompanyRow(implicit e0: GR[CompanyId], e1: GR[String]): GR[CompanyRow] = GR{
    prs => import prs._
    CompanyRow.tupled((<<[CompanyId], <<[String]))
  }
  /** Table description of table company. Objects of this class serve as prototypes for rows in queries. */
  class Company(tag: Tag) extends Table[CompanyRow](tag, "company") {
    def * = (companyId, name) <> (CompanyRow.tupled, CompanyRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (companyId.?, name.?).shaped.<>({r=>import r._; _1.map(_=> CompanyRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column company_id  */
    val companyId: Column[CompanyId] = column[CompanyId]("company_id")
    /** Database column name  */
    val name: Column[String] = column[String]("name")
  }
  /** Collection-like TableQuery object for table Company */
  lazy val Company = new TableQuery(tag => new Company(tag))
  
  /** Entity class storing rows of table Trader
   *  @param traderId Database column trader_id 
   *  @param email Database column email  */
  case class TraderRow(traderId: TraderId, email: String)
  /** GetResult implicit for fetching TraderRow objects using plain SQL queries */
  implicit def GetResultTraderRow(implicit e0: GR[TraderId], e1: GR[String]): GR[TraderRow] = GR{
    prs => import prs._
    TraderRow.tupled((<<[TraderId], <<[String]))
  }
  /** Table description of table trader. Objects of this class serve as prototypes for rows in queries. */
  class Trader(tag: Tag) extends Table[TraderRow](tag, "trader") {
    def * = (traderId, email) <> (TraderRow.tupled, TraderRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (traderId.?, email.?).shaped.<>({r=>import r._; _1.map(_=> TraderRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column trader_id  */
    val traderId: Column[TraderId] = column[TraderId]("trader_id")
    /** Database column email  */
    val email: Column[String] = column[String]("email")
  }
  /** Collection-like TableQuery object for table Trader */
  lazy val Trader = new TableQuery(tag => new Trader(tag))
}
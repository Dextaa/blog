import sbt.Keys._
import sbt._

import scala.slick.driver.MySQLDriver
import scala.slick.jdbc.meta.createModel
import scala.slick.model.Model
import scala.slick.model.codegen.SourceCodeGenerator

object SlickMetadataBuild extends Build {

  val IncludedTables = Seq("buy", "company", "trader")
  val ColumnMappings = Map ("buy.trader_id" -> "TraderId",
                            "buy.company_id" -> "CompanyId",
                            "company.company_id" -> "CompanyId",
                            "trader.trader_id" -> "TraderId")

  val Driver = MySQLDriver

  lazy val slick = TaskKey[Unit]("slick-gen", "Generates the Slick metadata")
  lazy val slickCodeGenTask = (scalaSource in Compile) map { sourceDir =>

      val outputDir = sourceDir.getPath
      val packageName = "com.github.dextaa.ids.model.slick.generated"
      val fileName = "SchemaMetadata.scala"
      val entityName = "Schema"
      val slickProfile = "scala.slick.driver.MySQLDriver"

      database.withSession{ implicit session =>
        val allTables = Driver.getTables.list
        val model = createModel(allTables.
                                filter(t => IncludedTables contains t.name.name),
                                Driver)
        val codegen = customisedGenerator(model)
        codegen.writeToFile(slickProfile, outputDir, packageName, entityName, fileName)
      }
  }

  private def customisedGenerator(model: Model) = new SourceCodeGenerator(model)
  {
    override def code =
      "import com.github.dextaa.ids.model.slick.mapping.MySqlIdentity._\n" +
      "import com.github.dextaa.ids.model.api.types._\n" + super.code

    override def Table = new Table(_) {
      override def Column = new Column(_) {
        override def rawType = {
          val qualifiedColumnName = s"${model.table.table}.${model.name}"
          ColumnMappings.get(qualifiedColumnName).getOrElse(super.rawType)
        }
      }
    }
  }

  private def database = {
    val url = "jdbc:mysql://localhost/typesafe_ids?user=demo_user&password=letmein"
    Driver.simple.Database.forURL(url, driver = "com.mysql.jdbc.Driver")
  }

  //private def uncustomisedGenerator(model: Model) = new SourceCodeGenerator(model)

}
package com.github.dextaa.ids.model.slick

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.driver.MySQLDriver.simple.{Database => SlickDatabase}

/**
 * This is obviously just a very quick way to access a database
 * using Slick, suitable only for example code.
 */
object Database {

  private val url = "jdbc:mysql://localhost/typesafe_ids?user=demo_user&password=letmein"
  private val db = SlickDatabase.forURL(url, driver = "com.mysql.jdbc.Driver")

  def withSession[T](f: Session => T) = db withSession {session => f(session)}

}

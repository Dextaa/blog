name := "typesafe-ids"

version := "1.0-SNAPSHOT"

slick <<= slickCodeGenTask

val prodDependencies = Seq(
  "mysql" % "mysql-connector-java" % "5.1.6",
  "com.typesafe.slick" %% "slick" % "2.0.1",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)

val testDependencies = Seq(
  "org.scalatest" % "scalatest_2.10" % "2.1.5" % "test"
)

libraryDependencies ++= prodDependencies

libraryDependencies ++= testDependencies
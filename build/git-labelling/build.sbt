name := """git-labelling"""

val (revision, tag) = buildDescriptor.gitHeadRevisionAndTag()

version := "1.0-" + tag.getOrElse(revision)

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  ws,
  "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
)

buildInfo <<= buildInfoTask
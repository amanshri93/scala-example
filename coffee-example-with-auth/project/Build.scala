import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "coffee-example-with-auth"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    jdbc,
    "org.scalatest" % "scalatest_2.10" % "2.0.M6-SNAP9" % "test",
    "com.typesafe" % "slick_2.10" % "1.0.0-RC2",
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    "com.h2database" % "h2" % "1.3.166",
    "org.xerial" % "sqlite-jdbc" % "3.6.20")

  val main = play.Project(appName, appVersion, appDependencies).settings( // Add your own project settings here      
  )

}

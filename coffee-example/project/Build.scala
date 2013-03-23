import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "coffee-example"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    jdbc,
    "org.scalamock" %% "scalamock-scalatest-support" % "3.0" % "test",
    "com.typesafe" % "slick_2.10.0-RC1" % "0.11.2",
    "org.slf4j" % "slf4j-nop" % "1.6.6",
    "org.slf4j" % "slf4j-api" % "1.6.6",
    "com.h2database" % "h2" % "1.3.166",
    "org.xerial" % "sqlite-jdbc" % "3.6.20")

  val main = play.Project(appName, appVersion, appDependencies).settings( // Add your own project settings here      
    //scalaVersion := "2.10.1"
  )

}

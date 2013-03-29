import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "coffee-example-with-ldap"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    jdbc,
    "jp.t2v" %% "play2.auth"      % "0.9",
    "jp.t2v" %% "play2.auth.test" % "0.9" % "test",
    "com.unboundid" % "unboundid-ldapsdk" % "2.3.1",
    "org.scalamock" %% "scalamock-scalatest-support" % "3.0" % "test",
    "com.typesafe" % "slick_2.10" % "1.0.0-RC2",
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    "com.h2database" % "h2" % "1.3.166",
    "org.xerial" % "sqlite-jdbc" % "3.6.20")

  val main = play.Project(appName, appVersion, appDependencies).settings( // Add your own project settings here      
  )

}

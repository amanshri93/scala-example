package models

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import scala.slick.driver.H2Driver.simple._
import Database.threadLocalSession
import org.scalatest.exceptions.TestFailedException
import play.api.db.DB
import play.api.Play.current
import play.api.test._
import play.api.test.Helpers._
import util.InitTrait
import dal._

class DomainSpec extends FunSpec with ShouldMatchers with InitTrait {

  lazy val database = Database.forDataSource(DB.getDataSource())

  describe("Coffee Data Access") {

    it("should find Coffee object for a given Coffee name") {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        database withSession {
          init

          val cc = new CoffeeComponentImpl()
          val c = cc.find("Colombian")
          c.first.price should equal(799L)
        }
      }
    }
  }
}

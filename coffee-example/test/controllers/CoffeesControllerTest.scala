package controllers

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import play.api.test._
import play.api.test.Helpers._
import dal.CoffeeComponent

class CoffeesControllerTest extends FunSpec with ShouldMatchers with MockFactory {

  describe("Coffee Controller with Mock test") {

    it("should delete a coffee record with assert on status as SEE_OTHER") {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        val mockComponent = mock[CoffeeComponent]
        (mockComponent.delete _).expects("Columbian") returning (1) twice
        val controller = new CoffeesController(mockComponent)
        mockComponent.delete("Columbian") should equal(1)
        val result = controller.delete("Columbian")(FakeRequest())
        status(result) should equal(SEE_OTHER)
      }
    }

    it("POST /coffees/Colombian/delete should return SEE_OTHER") {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {

        val Some(result) = route(FakeRequest(POST, "/coffees/Colombian/delete"))
        status(result) should equal(SEE_OTHER)
      }
    }

    it("POST wrong url /coffees/Colombian/delete1 should return None") {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {

        val result = route(FakeRequest(POST, "/coffees/Colombian/delete1"))
        result should equal(None)
      }
    }
  }
}
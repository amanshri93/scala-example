package controllers

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import play.api.test._
import play.api.test.Helpers._
import dal.CoffeeComponent

class CoffeesControllerTest extends FunSpec with ShouldMatchers with MockFactory {
  describe("Coffee Data Access") {

    it("should find Coffee object for a given Coffee name") {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        val mockComponent = mock[CoffeeComponent]
        (mockComponent.delete _).expects("Columbian") returning (1) twice
        val controller = new CoffeesController(mockComponent)
        mockComponent.delete("Columbian") should equal (1)
        val result = controller.delete("Columbian")(FakeRequest())
        status(result) should equal (SEE_OTHER)
      }
    }
  }
}
package util

import scala.slick.driver.H2Driver.simple._
import Database.threadLocalSession

import play.api.db.DB
import play.api.Play.current
import play.api.test._
import play.api.test.Helpers._
import models._

trait InitTrait {

  def init = {
    val ddl = (Suppliers.ddl ++ Coffees.ddl)

    ddl.drop
    ddl.create
    // Insert some suppliers
    Suppliers.insertAll(
      Supplier(Some(101), "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
      Supplier(Some(49), "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
      Supplier(Some(150), "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966"))

    Coffees.insertAll(
      Coffee(Some("Colombian"), 101, 799, 0, 0),
      Coffee(Some("French_Roast"), 49, 899, 0, 0),
      Coffee(Some("Espresso"), 150, 999, 0, 0),
      Coffee(Some("Colombian_Decaf"), 101, 899, 0, 0),
      Coffee(Some("French_Roast_Decaf"), 49, 999, 0, 0))
  }
}
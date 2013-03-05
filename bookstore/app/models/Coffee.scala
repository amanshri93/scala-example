package models

import play.api.db.slick.Config.driver._

case class Coffee(name: String, supID: Int, price: Double, sales: Int, total: Int)

object Coffees extends Table[Coffee]("COFFEES") {
  def name = column[String]("COF_NAME", O.PrimaryKey)
  def supID = column[Int]("SUP_ID")
  def price = column[Double]("PRICE")
  def sales = column[Int]("SALES")
  def total = column[Int]("TOTAL")
  def * = name ~ supID ~ price ~ sales ~ total <> (Coffee.apply _, Coffee.unapply _)
  // A reified foreign key relation that can be navigated to create a join
  def supplier = foreignKey("SUP_FK", supID, Suppliers)(_.id)
}
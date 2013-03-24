package dal

import models._
import scala.slick.lifted.Query

import scala.slick.driver.H2Driver.simple._
import Database.threadLocalSession

trait CoffeeComponent {
  def find(pk: String): Query[Coffees.type, Coffee]
  def findall(pk: String): Query[(Coffees.type, Suppliers.type), (Coffee, Supplier)]
  def list(page: Int, pageSize: Int, orderBy: Int, filter: String): Query[(Coffees.type, Suppliers.type), (Coffee, Supplier)]
  def delete(pk: String): Int
}

class CoffeeComponentImpl extends CoffeeComponent {

  def find(pk: String): Query[Coffees.type, Coffee] = {
    Coffees.findByPK(pk)
  }

  def findall(pk: String): Query[(Coffees.type, Suppliers.type), (Coffee, Supplier)] = {
    Coffees.findAll(pk)
  }

  def list(page: Int, pageSize: Int, orderBy: Int, filter: String): Query[(Coffees.type, Suppliers.type), (Coffee, Supplier)] = {
    Coffees.list(page, pageSize, orderBy, filter)
  }

  def delete(pk: String): Int = {
    find(pk).delete
  }
}

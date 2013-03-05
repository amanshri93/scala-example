package test

import org.specs2.mutable._

import play.api.db.slick.DB
import play.api.test._
import play.api.test.Helpers._
import models._

/**
  * test the kitty cat database
  */
class CoffeeSpec extends Specification { 

  "DB" should {
    "work as expected" in new WithApplication {

      import play.api.db.slick.Config.driver.simple._

      DB.withSession{ implicit session =>
        
        val testSuppliers = Seq(
			Supplier(101, "Acme, Inc.",      "99 Market Street", "Groundsville", "CA", "95199"),
			Supplier( 49, "Superior Coffee", "1 Party Place",    "Mendocino",    "CA", "95460"),
			Supplier(150, "The High Ground", "100 Coffee Lane",  "Meadows",      "CA", "93966")
        )
        Suppliers.insertAll( testSuppliers: _*)
        
        val testCoffees= Seq(
		  Coffee("Colombian",         101, 7.99, 0, 0),
		  Coffee("French_Roast",       49, 8.99, 0, 0),
		  Coffee("Espresso",          150, 9.99, 0, 0),
		  Coffee("Colombian_Decaf",   101, 8.99, 0, 0),
		  Coffee("French_Roast_Decaf", 49, 9.99, 0, 0)
        )
        Coffees.insertAll( testCoffees: _*)
        Query(Coffees).list must equalTo(testCoffees)
        
        val q1 = for { c <- Coffees if c.price < 10.0 } yield (c.name)
 
        q1 foreach println
        println("**************");
        
        val q2 = for { c <- Coffees if c.price < 9.0 
          s <- c.supplier } yield (c.name, s.name)
        
        q2 foreach println
        println("**************");
        
        val q3 = for {
		  (c, s) <- Coffees zip Suppliers
		} yield (c.name, s.name)

		q3 foreach println
        println("**************");
		
		val q4 = Query(Coffees).filter(_.price < 8.0)
		val q5 = Query(Coffees).filter(_.price > 9.0)
		val unionQuery = q4 union q5
		unionQuery foreach println
        println("**************");

		val unionAllQuery = q4 unionAll q5
		unionAllQuery foreach println
        println("**************");
        
        val r = (for {
          c <- Coffees
          s <- c.supplier
        } yield (c, s)).groupBy(_._1.supID)
    
        val r1 = r.map { case (supID, css) =>
          (supID, css.length, css.map(_._1.price).avg)
        }
        
		r1 foreach println
      }
    }
  }
}

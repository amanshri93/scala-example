Play 2.1 integration with Slick
===============================

In this blog I have integrated Slick with latest Play. To test this execute `play test` you will see all the tests pass.

This sample talks about the classic Coffee/Supplier sample demonstrated with [Slick examples](http://slick.typesafe.com/doc/1.0.0/gettingstarted.html).

I have demonstrated capabilities like,

* Simple queries
* Joins
* Group by
* Aggrigation 

Refer CoffeeSpec.scala for more details.

I will be quickly putting a blog on this shortly.

To integrate Slick with your Play code, copy the slick-plugin folder in you play directory and modify,

    val main = play.Project(appName, appVersion, appDependencies).settings(
        // Add your own project settings here
    ).dependsOn(RootProject(file("slick-plugin/")))

And add the above code in project/Build.scala.

For more detials refer [Integrating Play 2.1 with Slick 1.0.0 Database query DSL](http://krishnasblog.com/2013/03/06/integrating-play-2-1-with-slick-1-0-0-database-query-dsl/).



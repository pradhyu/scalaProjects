
import java.io.InputStream

import scala.io.Source


// till scala 2.11 you have to use a stream
// make sure Make project is turned on to make it work in the scala
// work sheet
val stream : InputStream = getClass.getResourceAsStream("/resource.txt")
val lines = scala.io.Source.fromInputStream( stream ).getLines
lines.foreach(println)





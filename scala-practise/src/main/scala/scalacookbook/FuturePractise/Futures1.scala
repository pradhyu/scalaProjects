package scalacookbook.FuturePractise

/**
  * Created by i65626 on 7/18/2017.
  */

// imports
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Futures1 extends App {
  // used by 'time' method
  implicit val baseTime = System.currentTimeMillis()

  // 2 create a future
  /*
  A Future is created after the second comment. Creating a Future is simple; you just pass it a block of code you
  want to run. This is the code that will be executed at some point in the future.
   */
  val f = Future {
    Thread.sleep(500)
    1 + 1
  }

  println("Started the future process")
  // 3 - this is blocking (blocking is bad)
  /*
  The Await.result method call declares that it will wait for up to one second for the Future to return.
  If the Future doesn’t return within that time, it throws a java.util.concurrent.TimeoutException
   */
  println("Awaiting future to complete")
  val result = Await.result(f, 1 second)
  println("waiting done")
  println(result)

  /*  The sleep statement at the end of the code is used so the program will keep running while the
    Future is off being calculated. You won’t need this in real-world programs, but in small example programs like this,
     you have to keep the JVM running.

   */
  Thread.sleep(1000)

}

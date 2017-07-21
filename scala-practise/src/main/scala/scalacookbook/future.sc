

// imports
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Futures1 extends App {
  // used by 'time' method
 implicit val baseTime=System.currentTimeMillis()

  // 2 create a future

  val f= Future {
    Thread.sleep(5000)
    1+ 1
  }


  // 3 - this is blocking (blocking is bad)

  val result = Await.result(f, 1 second)
  println(result)
  Thread.sleep(1000)

}



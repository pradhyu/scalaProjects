package scalacookbook.FuturePractise

/**
  * Created by i65626 on 7/18/2017.
  */

import scala.concurrent.{Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.util.Random

object Example1 extends App {
  def sleep(value: Long) = {
    Thread.sleep(value)
  }

  println("starting calculation ...")
  val f = Future {
    sleep(Random.nextInt(500))
    42
  }
  println("before onComplete")
  f.onComplete {
    case Success(value) => println(s"Got the callback, meaning = $value")
    case Failure(e) => e.printStackTrace
  }
  // do the rest of your work
  (1 until 10).foreach(i => {
    println(s"$i - Start")
    sleep(100)
    println(s"$i - End..")
  }
  )
}


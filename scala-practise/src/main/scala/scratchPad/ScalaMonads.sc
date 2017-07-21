/*
Scala monads
We will model a monad with a generic trait that provides
methods unit() and flatMap().
We can call it M instead of Monad simply to be more concise. Here it is:
implementing the trait
class StringM extends M[String] {
  override def flatMap[B](f: (String) => M[B]): M[B] = {
    unit("Testing")
  }
}

def flatMap[B] (f: (A) => U[B]) : U[B]
val f = (i: Int) => List( i -1, i, i+1)
 */

trait M[A] {
  def flatMap[B](f: A => M[B]): M[B]
}

//def unit[A](x: A): M[A]
//
val f = (item: Int) => List(item - 1, item, item + 1)
val listOfIntegers = List(1, 2, 3, 4, 5)
println("flatmap:" + listOfIntegers.flatMap(f))
println("map: " + listOfIntegers.map(f))

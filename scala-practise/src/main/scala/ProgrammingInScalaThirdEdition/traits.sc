import scala.collection.mutable.ArrayBuffer

/*
  Traits in scala
  Traits are like interface in java but could contain implementation
  traits when call super function it calls the runtime super function in
  concrete class.

 */
// Integer queue.
abstract class IntQueue {
  def put(x: Int)

  def get(): Int
}

// Basic queue implementation
// Concrete classf
class BasicIntQueue extends IntQueue {

  import scala.collection.mutable.ArrayBuffer

  val arrayBuf = new ArrayBuffer[Int]

  // remove first element in the queue and return it
  def get() = arrayBuf.remove(0)

  def put(x: Int) = {
    arrayBuf += x
  }

  override def toString: String = arrayBuf.toString()
}

// simple queue
val bq = new BasicIntQueue
bq.put(1)
bq.put(2)
bq.put(-1)
// FIFO
assert(bq.toString == ArrayBuffer(1, 2, -1).toString)
bq.toString

// Now we will create a trait to mix it with BasicIntQueue
// and see how we can achieve various functionality

// using a trait create a functionality to guard agains -ve number insertion
// to the BasicIntQueue
// this trait is only usable to subclasses that extends IntQueue since it extends
// IntQueue
trait NegativeNumberFilter extends IntQueue {
  abstract override def put(x: Int): Unit = {
    if (x >= 0) {
      super.put(x)
    } else {
      println("Can't insert negative number  " + x)
    }
  }
}

// either create new class with this filter or just use with clause

var nonNegBq = new BasicIntQueue with NegativeNumberFilter
nonNegBq.put(1)
nonNegBq.put(-2)
nonNegBq.put(3)
nonNegBq.toString
// -3 won't be inserted
assert(nonNegBq.toString == ArrayBuffer(1,3).toString)

// multiple traits tes
// double the number before inserting to queue

trait SquareIt extends IntQueue {
  abstract override def put(x: Int): Unit = {
    super.put(x * x)
  }
}

// the with traits get applied from right to left when calling super
// if super is called in trait it calls super in the left in the stack
var nonNegDoubleBq = new BasicIntQueue with NegativeNumberFilter with SquareIt
nonNegDoubleBq.put(1)
nonNegDoubleBq.put(-2)
nonNegDoubleBq.put(3)
nonNegDoubleBq.toString
// -3 won't be inserted
assert(nonNegDoubleBq.toString == ArrayBuffer(1, 4, 9).toString)


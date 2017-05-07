

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
assert(bq.get() == 1)
assert(bq.get() == 2)
assert(bq.get() == -1)
bq.toString

// Now we will create a trait to mix it with BasicIntQueue
// and see how we can achieve various functionality

// using a trait create a functionality to guard agains -ve number insertion
// to the BasicIntQueue
// this trait is only usable to subclasses that extends IntQueue since it extends
// IntQueue
trait NegativeNumberFilter extends IntQueue {
  abstract override def put(x: Int): Unit = {
    if (x >= 0) super.put(x)
  }
}

// either create new class with this filter or just use with clause

var nonNegBq = new BasicIntQueue with NegativeNumberFilter
nonNegBq.put(1)
nonNegBq.put(-2)
nonNegBq.put(3)
nonNegBq.toString
// -3 won't be inserted
assert(nonNegBq.get() == 1)
assert(nonNegBq.get() == 3)

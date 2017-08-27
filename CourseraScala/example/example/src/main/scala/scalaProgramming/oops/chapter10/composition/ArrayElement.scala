package scalaProgramming.oops.chapter10.composition

/*
   Composition and Inheritance
 */
// fields and methods can't have same name in scala
// only two name space in scala
// one for values(package, field, method and singleton object)
// other one for type ( class , traits)
// override the val with the class parameter rather than
// having to create a field inside the class
class ArrayElement(override val cont: Array[String]) extends Element {
  // another way is to define the class param and then create a filed where
  // you assign the value to override the superclass method cont.
  // val cont: Array[String] = cont // something like this

  println("ArrayElement Initialized")
  def printContent(): Unit = {
    println(cont.mkString(","))
  }

  // override is a must in scala not optional like in java
  //and unnecessary override throws compile time error

  /*
  override def hidden() {
    This throws compile error as there's non hidden
    in super class that this class could override
  }
  */

}


object ArrayElement {

  def main(args: Array[String]) {
    val ae = new ArrayElement(Array("one", "two", "three"))
    ae.printContent()
  }

}


// now creating a lineElement that takes string as input
// the class definition, ie type definition must call the super constructor

class LineElement(line: String) extends ArrayElement(Array(line)) {

  println("LineElement is Initialized")

}

object LineElement {
  def main(args: Array[String]): Unit = {
    val le = new LineElement("line")
    le.printContent()
  }
}

/*
10.9 POLYMORPHISM AND DYNAMIC BINDING
 */
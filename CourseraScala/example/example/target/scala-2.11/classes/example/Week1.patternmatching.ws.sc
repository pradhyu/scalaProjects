// what's type of
//{case (key , value ) => key + ":" + value}
// JBinding => String,
/*
 expands to
new Function1 [JBinding, String ] {
def apply (x: Jbinding) = x match {
case (key, value ) => key + ":" + show (value)
}
}
 */
// subclassing Functions

/// traits extends function
// trait Map [ Key, Value] extends (Key => value )..

// don't use package in idea

println("Welcome to scala worksheet")
val f: String => String = {
  case "ping" => "pong"
}

f("ping")
// f("pong") // throws error

// Partial Function
// also check if the function is defined for
val fPar: PartialFunction[String, String] = {
  case "ping" => "pong"
}
fPar.isDefinedAt("ping")
fPar.isDefinedAt("poing")
/*
The partial function trait is defined as
trait PartialFunction [-A, +R] extends Function1[-A, +R] {
def apply (x: A) : R
def isDefined (x:A): R
}

 */
// assign  values to all there variables ,
// same value
val x, y, rest = (List(1), List(2), List(3))

// Scala collection
println(x)
println(y)

// case clause practisy


// Lazy Evaluation


// structural induction on Trees.
// inSets.
/*
abstract class IntSet{
def incl(x: Int): IntSet
  def contains(x: Int): Boolean
}
*/
/*
object Empty extends IntSet {
def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = NonEmpty (x, Empty, Empty)
}
case class NonEmpty (elem: Int, left: IntSet, right: IntSet) extends Inset {

}
*/



def factorial(n: Int): Int = {

  @annotation.tailrec
  def go(n: Int, acc: Int): Int =
    if (n <= 0) acc
    else go(n - 1, n * acc)

  go(n, 1)
}

// tail recuision using annotation
// if all recursive calls made by a function are in tail position, scala
// automatically compiles the recursion to iterative loops that don't consume
// call stack frames for each iteration.
// otherwise you hve to specify the annotation.tailrec as annotation
factorial(10)


// monomorphic functions, ie function that operate on only one type of data
def findFirst(ss: Array[String], key: String): Int = {
  @annotation.tailrec
  def loop(n: Int): Int =
    if (n >= ss.length) -1
    else if (ss(n) == key) n
    else loop(n + 1)

  loop(0)
}

findFirst(Array("a", "b", "c"), "b")


// Polymorphic function to find an element in an array
def findFirstGeneric[A](as: Array[A], p: A => Boolean): Int = {
  @annotation.tailrec
  def loop(n: Int): Int =
    if (n >= as.length) -1
    else if (p(as(n))) n
    else loop(n + 1)

  loop(0)
}
// calling HOFs with anonymous function
// Generic function
findFirstGeneric(Array(7, 1, 2, 3), (x: Int) => x == 2)
findFirstGeneric(Array("z", "b", "c", "d"), (x: String) => x == "d")

/*
Implement isSorted, which checks whether an Array[A] is sorted according to a given comparison function:

def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean

 */


// Functions as values in Scala
val lessThan = new Function2[Int, Int, Boolean] {
  def apply(a: Int, b: Int) = a < b
}
val b = lessThan(10, 20)
val c = lessThan.apply(100, 20)

// Function2 is just an ordinary trait ( an interface) provided by the standard Scala Library
// to represent function objects that take two arguments.


// Following types to implementations
def partial1[A, B, C](a: A, f: (A, B) => C): B => C =
  (b: B) => f(a, b)
// returns function B => C


// currying

/*
Let’s look at another example, currying,[9] which converts a function f of two arguments into
a function of one argument that partially applies f.
Here again there’s only one implementation that compiles. Write this implementation.

 */

def curry[A, B, C](f: (A, B) => C): A => (B => C) =
  a => b => f(a,b)

curry((x: Int, y: Int) => x + y)(1)(2)

/*
Exercise 2.4
Implement uncurry, which reverses the transformation of
curry. Note that since => associates to the right,
 A => (B => C) can be written as A => B => C.

 */
def uncurry[A, B, C](f: A => B => C): (A, B) => C =
  (a,b) => f(a)(b)

/*
Exercise 2.5
Implement the higher-order function that composes two functions.
 */

def compose[A, B, C](f: B => C, g: A => B): A => C=
  a => f(g(a))


// sample
val f = (x: Double) => math.Pi / 2 -x
val cos = f andThen math.sin
cos(90)


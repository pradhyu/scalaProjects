
// Collection
// Idealized implementation of map on Lists
/*
abstract class List[+T] {
  def map[U](f: T => U): List[U] = this match {
    case x :: xs => f(x) :: xs.map(f)
    case Nil => Nil
  }

  // Idealized Implementation of flatMap on Lists
  def flatMap[U](f: T => List[U]): List[U] = this match {
    case x :: xs => f(x) ++ xs.flatMap(f)
    case Nil => Nil
  }

  def filter(;: T => Boolean
  ): List[T] = this match {
    case x :: xs =>
      if (p(x)) x :: xs.filter(p) else xs.filter(p)
    case Nil => Nil
  }
}
*/


// For -Expression

// Simplify combinations of core methods map, flatMap, filter,
// instead of :
def testFn1(n: Int) = {
  def isPrime(i: Int): Boolean = true

  (1 until n) flatMap (i =>
    (1 until i) filter (j => isPrime(i + j)) map
      (j => (i, j)))
}

testFn1(4)
// can be better written with a for loop
def testFn1_good(n: Int) = {
  def isPrime(i: Int): Boolean = true

  for {i <- 1 until n
       j <- 1 until i
       if isPrime(i + j)
  } yield (i, j)
}
testFn1_good(4)

/*
The scala compiler translates for-expressions in terms of map, flatMap and a lazy variant of filter.
Here is the translation schema used by the compiler
1. A simple for-expression
 */
val e1 = List(1, 2)
val e2 = 0
for (x <- e1) yield e2
// is translated to
e1.map(x => e2)

/*
2. A for-expression
for (x <- e1 if f; s) yield e2
where f is a filter and s is a (potentially empty ) sequence of generators and filters, is translated to

for (x <- e1.withFilters(x=> f); s) yield e2
( and the translation  continues with the new expression)
You can think of withFilter as a variant of filter that doesn't produce an intermediate list, ut instead filters the following map or flatMap function application.

*/

/*
Translation of For (3)
with two generator
for (x <- e1; y <- e2; s ) yield e3
where s is a (potentially empty) sequence of generators and filters, is translated into
e1.flatMap(x => for (y <- e2; s) yield e3)
(and the translation continues with the new expression)
 */
for {
  (obj) <- List(1, 2, 3)} yield obj

// Translation of Pattern Matching in For
for {
  x <- 2 to 4
  y <- 2 to 4
  if (x % y == 0)
} yield (x, y)
// converted to map
(2 to 4) flatMap (x =>
  (2 to 4) withFilter (y =>
    x % y == 0) map (y => (x, y)))

// Queries with For
//  A Mini-Dataase
case class Book(title: String, authors: List[String])

var books: List[Book] = List(
  Book(title = "Structure and Interpretation of Computer Programs",
    authors = List("Abelson, Harald", "sussman, Gerald J.")),
  Book(title = "Introduction to Functional Programming",
    authors = List("Bird, richard", "Wadler, Phil")),
  Book(title = "Effective Java",
    authors = List("Bloch, Joshua")),
  Book(title = "Java Puzzlers",
    authors = List("Bloch, Joshua", "Gafter, Neal")),
  Book(title = "Programming in Scala",
    authors = List("Odersk, Martin", "Spon, Lex", "Venners, Bill")))

for {b <- books; a <- b.authors if a.startsWith("Bird")} yield b.title

" testing " indexOf "ing"
for (b <- books if (b.title.indexOf("Program") >= 0))
  yield b.title

List(1, "one")

// author that published two books
for {
  b1 <- books
  b2 <- books
  if b1 != b2
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield a1

// the solution shows a1 twice when the condition match
// how can we fix it ??
for {
  b1 <- books
  b2 <- books
  if b1.title < b2.title
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield a1

// even with this if we have author that wrote 3 books
books = List(
  Book(title = "Structure and Interpretation of Computer Programs",
    authors = List("Abelson, Harald", "sussman, Gerald J.")),
  Book(title = "Introduction to Functional Programming",
    authors = List("Bird, richard", "Wadler, Phil")),
  Book(title = "Effective Java",
    authors = List("Bloch, Joshua")),
  Book(title = "Java Puzzlers",
    authors = List("Bloch, Joshua", "Gafter, Neal")),
  Book(title = "Java Puzzlers two ",
    authors = List("Bloch, Joshua", "Gafter, Neal")),
  Book(title = "Programming in Scala",
    authors = List("Odersk, Martin", "Spon, Lex", "Venners, Bill")))
for {
  b1 <- books
  b2 <- books
  if b1.title < b2.title
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield (a1)

// this is a design flaws using set instead of List will resolve it
val booksSet = Set(
  Book(title = "Structure and Interpretation of Computer Programs",
    authors = List("Abelson, Harald", "sussman, Gerald J.")),
  Book(title = "Introduction to Functional Programming",
    authors = List("Bird, richard", "Wadler, Phil")),
  Book(title = "Effective Java",
    authors = List("Bloch, Joshua")),
  Book(title = "Java Puzzlers",
    authors = List("Bloch, Joshua", "Gafter, Neal")),
  Book(title = "Java Puzzlers two ",
    authors = List("Bloch, Joshua", "Gafter, Neal")),
  Book(title = "Programming in Scala",
    authors = List("Odersk, Martin", "Spon, Lex", "Venners, Bill")))

for {
  b1 <- booksSet
  b2 <- booksSet
  if b1.title < b2.title
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield (a1)

// so sometimes fixing datastructure you use resolves lots of issues.
// for and map yeids same type that they iterate, if input set output is set, if input is List output is List

// Implementation of for expression
// maps for expression to certain expression of high order function
// map , flatMap and filter

// For-Expressions and Higher-Order Functions

def mapFun[T, U] (xs: List[T], f: T => U): List[U] =
for (x <-xs) yield f(x)


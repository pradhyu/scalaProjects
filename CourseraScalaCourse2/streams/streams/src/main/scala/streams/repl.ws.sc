//This will return None if the list isn't long enough to provide that element, and Some(value) if it is.

val l = List("a", "b", "c")
l.lift(1).getOrElse("-")
l.lift(5).getOrElse(("-"))


val terrain = Vector(Vector('S', 'T'), Vector('o', 'o'), Vector('o', 'o'))

// use view for lazy evaluation

//(1 to 1000000000).filter(_ % 2 == 0).take(10).toList
//java.lang.OutOfMemoryError: GC overhead limit exceeded

// Here Scala tries to create a collection with 1000000000 elements to then access the first 10. But with view:

(1 to 1000000000).view.filter(_ % 2 == 0).take(10).toList
//res2: List[Int] = List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)

(Stream (2, 3, 4) #::: Stream(5, 6, 7)).toList

List(2, 3, 4) ++ List(5, 6, 7)

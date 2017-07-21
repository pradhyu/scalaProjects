

def isEven(n: Int) = {
  (n % 2) == 0
}
List(1, 2, 3, 4).filter(isEven).foreach(println)
(0 until 100).filter(isEven).foreach(println)



// simple pattern matching
def checkY(y: Int) = {
  for {
    x <- Seq(99, 100, 101)
  } {
    val str = x match {
      case `y` => "found y!" // The only change: `y`
      case i: Int => "int: " + i
    }
    println(str)
  }
}
checkY(100)


// multiple cases in one
for {
  x <- Seq(1, 2, 2.7, "one", "two", 'four)
} {
  val str = x match {
    case _: Int | _: Double => "a number: " + x
    case "one" => "string one"
    case _: String => "other string: " + x
    case _ => "unexpected value: " + x
  }
  println(str)
}

// recursion using pattern matching


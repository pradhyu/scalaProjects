import scala.collection.JavaConverters._

object scalaForInpatient {
  // for each
  for (i <- "Hello") println(i)

  // generators
  for (i <- 1 to 4; j <- 1 to 3) print(i + ": " + j)

  // guards , if clause
  for (i <- 1 to 100 if i == 99) print(i)

  // Yeild // returns the collection of the items after yield
  // return type is vector which is like Array or Arraylist
  for (i <- 1 to 10) yield i % 3

  // Functions :  Defining Functions
  // Methods and functions both available in scala.
  // Methods inside class, function without class.
  def abs(x: Double) = if (x >= 0) x else -x

  abs(-2)
  abs(2)
  // return typeo for abs is infered since we didn'd define it in the function
  // For recursive function you must define the return type

  def fac(n: Int): Int = if (n <= 0) 1 else n * fac(n - 1)

  fac(2)

  /* if function doesn't compute value but it's just for side effect
  // so retunr time is unit
  // if function doesn't return value it's unit function or procedure no = required
  // caution
  */
  def facErr(n: Int) { // Probably an error
    var r = 1
    for (i <- 1 to n) r = r * i
    r
  }

  facErr(3)

  // so = is must to return the value
  def facnoErr(n: Int) = { // Probably an error
    var r = 1
    for (i <- 1 to n) r = r * i
    r
  }

  facnoErr(3)

  // Named and Default Arguments

  def decorate(str: String, left: String = "[", right: String = "]") = left + str + right

  decorate("me")
  decorate(str = "me", right = "{", left = "}")


  // rest keys, Variable number or arguments indicated with * after type

  def sum(args: Int*): Int = { // args is a Seq[Int]}
    var result = 0
    for (arg <- args) result = result + arg
    result
  }

  sum(3, 3, 4)

  // if you want to pass seq to the same function it doesn't work
  //sum (1 to 10) // throws erro//
  // fix for it
  sum(1 to 10: _*)


  // vowels

  def isVowel(ch: Char): Boolean = {
    val vowelChars = "aeiou"
    vowelChars.contains(ch)
  }

  isVowel('a')

  // write a function that , given a string, returns a string of all of it's vowels. Use IsVowel.
  def vowels(string: String): String = {
    for (ch <- string if isVowel(ch)) yield ch
  }

  vowels("umbrella")

  // Same with recursive solution
  def vowelsRecur(string: String): String = {
    // base condition
    if (string.length == 0) return ""
    val firstChar = string(0)
    val restOfString = string.substring(1)
    if (isVowel(firstChar)) {
      firstChar + vowelsRecur(restOfString)
    } else {
      vowelsRecur(restOfString)
    }
  }

  vowelsRecur("uruguay")

  // same with while loop
  def vowelsWhileLoop(string: String): String = {
    var i = 0;
    var vowels = "";
    while (i < string.length) {
      if (isVowel(string(i))) vowels += string(i)
      i += 1
    }
    vowels
  }

  vowelsWhileLoop("uruguay")

  // Assignment vowels with default and ignorecase which defaults to "aeiou" and true
  // write a function that , given a string, returns a string of all of it's vowels. Use IsVowel.
  def isVowelWithDefaults(ch: Char, vowelChars: String): Boolean = {
    vowelChars.contains(ch)
  }

  def vowelsWithDefaults(string: String, vowelChars: String = "aeiou", ignoreCase: Boolean = true): String = {
    if (ignoreCase == true) vowelsWithDefaults(string.toLowerCase, vowelChars, ignoreCase = false)
    println("String to check for vowel: " + string)
    for (ch <- string if isVowelWithDefaults(ch, vowelChars)) yield ch
  }

  vowelsWithDefaults("aabXXXX", "bx")
  //vowelsWithDefaults("aabXXXX", "bx", false)
  vowelsWithDefaults("aabXXXX", "bx", true)
  vowelsWithDefaults("August")


  println("asdfasdfASS".toLowerCase)

  // I don't understand what's gooing on , moving on to the next

  // Collection
  // Array , Maps and Tuples

  // working with Array Buffer
  val nums = new Array[Int](10)
  // remember square brackes not angle brackets like java
  val a = Array("Hello", "World")
  // use parenthesis to access the value of the array
  a(0) = "GoodBye"

  val aa = new Array[Int](10)
  for (i <- 0 until aa.length) aa(i) = i * i
  aa


  // ArrayList has variable length, ArrayBuffer is something you would  use in scal

  // import ArrayBuffer to use it
  import scala.collection.mutable.ArrayBuffer

  val aBuf = new ArrayBuffer[Int]
  // some functionality in Array buffer
  // add element
  aBuf += 1
  // add multiple entries
  aBuf += (2, 3, 4)
  // add new array
  aBuf ++= Array(5, 6)
  // usual kind of methods like in java
  aBuf.insert(2, 6)
  aBuf
  aBuf.remove(2)
  aBuf
  // trimes last 5 elements
  aBuf.trimEnd(5)
  aBuf

  // convert from and to Array and Buffer
  // .toBuffer or .toArray   can be used to do the conversion

  // Transforming the data in Array Buffer

  // double the element sin an array
  val aRay = Array(1, 2, 3, 4, 5)
  val result = for (elem <- aRay) yield 2 * elem

  // Tons of built-in methods in Scala Array
  result.sum
  result.max
  result.reverse
  ArrayBuffer("P", "A").max

  result.toString
  // use mkString in scala, with the separator.
  result.mkString(",")
  result.mkString("[", ",", "]")

  // Map structures in scala
  // by default maps are immutable , if you need mutable explicitly declare it
  val scores = Map("Alice" -> 10, "Bob" -> 10)

  val scoreMut = scala.collection.mutable.Map("Alice" -> 10, "Bob" -> 20)

  // -> creates pairs
  "Alice" -> 10

  "PK" -> 1231

  // use immutable maps as far as possible

  val scores2 = scores + ("PK" -> 100)
  val scores3 = scores - "PK"

  // what do do when  you  have a map
  // how to iterate
  for ((k, v) <- scores)
    println(k + " has score " + v)


  // map yeilds map
  for ((k, v) <- scores) yield (v, k)
  // yeild gives same type as one it's iterating map is map, seq is seq, list is list etc..


  // Tuples
  // contains variety of type s
  val t = (1, "two", 3.13)
  t._2
  t._3
  // tuples are one based first element is 1

  // better to use pattern matching to get the value from tuples

  val (_, second, third) = t
  // ignore first value in the tuple

  // Lab excercise

  // Removing all but the first negative number
  // hint get the indexes of the numbers which are not negative, and ignore the first negative

  var inputBuf = ArrayBuffer(1, 2, -3, 4, -5, 6, -7, -8)

  inputBuf.collectFirst({ case i if (i > 1) => i })


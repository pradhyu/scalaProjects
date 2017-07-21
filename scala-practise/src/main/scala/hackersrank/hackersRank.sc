import scala.Predef.intArrayOps
// matrix handling in scala

def main(args: Array[String]) {
  //val sc = new java.util.Scanner(System.in);
  // var n = sc.nextInt();
  val n = 3 // by default 3
  val a = Array.ofDim[Int](n, n);
  for (a_i <- 0 to n - 1;
       a_j <- 0 to n - 1)
    yield {
      a(a_i)(a_j) = a_i + a_j
    }
  // primary Diagonal
  a.map(row => row.map(item => print(item)))
  a.flatMap(item => item).map(println)
}

main(Array("1", "2"))


var test = 1
test += 121


//https://www.hackerrank.com/challenges/plus-minus
object Solution1 {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();
    var arr = new Array[Int](n);
    for (arr_i <- 0 to n - 1) {
      arr(arr_i) = sc.nextInt();
    }
    var positiveCount, negativeCount, zeroCount = 0
    for (item <- arr)
      yield {
        if (item > 0) positiveCount += 1
        if (item < 0) negativeCount += 1
        if (item == 0) zeroCount += 1
      }
    val total: Float = (positiveCount + negativeCount + zeroCount)
    println(positiveCount / total)
    println(negativeCount / total)
    println(zeroCount / total)
  }
}

//https://www.hackerrank.com/challenges/staircase?h_r=next-challenge&h_v=zen
object Solution2 {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();
    for (i <- n - 1 until -1 by -1) {
      println(" " * i + "#" * (n - i))
    }
  }
}

//https://www.hackerrank.com/challenges/mini-max-sum
// if time allows
// TODO: fix the recursive function with tail recursion
object Solution {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var arr = new Array[Int](5);
    for (arr_i <- 0 to 5 - 1) {
      arr(arr_i) = sc.nextInt();
    }

    def sumBigInt(arrayToSum: Array[Int]): BigInt = {
      if (arrayToSum.length == 0) return 0
      val first = arrayToSum(0)
      val rest = arrayToSum.slice(1, arrayToSum.length)
      first + sumBigInt(rest)
    }

    val sortedArray = arr.sorted
    val minSum: BigInt = sumBigInt(sortedArray.take(4))
    val maxSum: BigInt = sumBigInt(sortedArray.reverse.take(4))
    print(minSum + " " + maxSum)

  }
}

val aray = Array("foo", "hoo", "goo", "ioo", "joo")
aray.slice(1, aray.length)
"testing".indexOf('t')

//
val arr = Array[String]("000X", "00000")


//https://www.hackerrank.com/contests/rookierank-3/challenges/find-the-bug
object Solution3 {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in);
    var n = sc.nextInt();
    var grid = new Array[String](n);
    for (grid_i <- 0 to n - 1) {
      grid(grid_i) = sc.next();
    }
    // only one row with bug
    val item = grid.zipWithIndex.filter(tuple => (tuple._1.contains("X")))(0)
    print(item._2 + "," + item._1.indexOf('X'))
    // Return an array containing [r, c]
  }
}

// bit Operation
// find loneley number
println("0 0 1 1 2 3 3 4 4".split(" ").map(_.toInt).foldLeft(0) {
  (a, b) => {
    println(s"a=${a}. b=${b} =${a ^ b}")
    a ^ b
  }
})


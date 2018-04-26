class Rational(n: Int, d: Int) {
  def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)

  }

  //  denominator must not  be zero
  require(d != 0)

  val g = gcd(n, d)
  val num = n / g
  val deno = d / g


  // if a user want to create a rational with only numerator
  def this(n: Int) = {
    this(n, 1)
  }

  def +(that: Rational): Rational = {
    new Rational((that.deno * this.num + this.deno * that.num), that.deno * this.deno)
  }

  // in case we want to add an integer to rational
  def +(that: Int): Rational = {
    new Rational((this.deno * that + this.num) / this.deno)
  }

  def -(that: Rational): Rational = {
    new Rational((that.deno * this.num - this.deno * that.num), that.deno * this.deno)
  }

  // multiply
  def *(that: Rational): Rational = {
    new Rational((this.num * that.num), (this.deno * that.deno))
  }

  // multiply by a constant
  def *(that: Int): Rational = {
    new Rational((this.num * that) / this.deno)

  }

  def *(that: Float): Rational = {
    new Rational((this.num * that.toInt) / this.deno)
  }

  override def toString(): String = {
    (s"$num/$deno")
  }
}

object Rational {
  implicit def intToRational(x: Int) = new Rational(x)

  implicit def flatToRational(f: Float) = new Rational(f.toInt)

  def main(args: Array[String]) {
    val ra1 = new Rational(2, 8)
    val ra2 = new Rational(4, 4)

    println(ra1 * ra2)
    println(2 * ra1)
    println(ra1 * 2.0f)
    println(2.0f * ra1) // need implicit conversion  to make this work
    println( 1 * 1000)
  }

}

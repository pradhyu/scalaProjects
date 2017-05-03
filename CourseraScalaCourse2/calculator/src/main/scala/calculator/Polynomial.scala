package calculator

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double],
                   c: Signal[Double]): Signal[Double] = {
    // Δ = b² - 4ac
    Signal(math.pow(b(), 2D) - 4 * a() * c())
  }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
                       c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    //(-b ± √Δ) / 2a

    val delta: Signal[Double] = computeDelta(a, b, c)
    if (delta() < 0)
      Signal(Set())
    else {
      val deltaSqrt = math.sqrt(delta())
      val firstSoln = (-b() + deltaSqrt) / (2 * a())
      val secondSoln = (-b() - deltaSqrt) / (2 * a())
      val solns: Set[Double] = Set[Double](firstSoln) + secondSoln
      Signal(solns)
    }
  }
}

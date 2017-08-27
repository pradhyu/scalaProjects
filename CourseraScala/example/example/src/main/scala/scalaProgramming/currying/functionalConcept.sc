

// Currying the function in scala

def adder1(x: Int, y: Int) = x + y






// assertion controlled with closure
val turnOnAssertion=false

// normal control
def normalAssertion(predicate: => Boolean): Unit = {
  if(turnOnAssertion==true && predicate) throw new AssertionError()
}
normalAssertion(true)
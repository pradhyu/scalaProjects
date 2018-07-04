package fastParse 

import org.scalatest._
import fastparse.all.Parsed

class fastParseExampleMathParsingSpec extends FlatSpec with Matchers {
  "Math parsing using fast parser 1+100" should "return 101" in {
    val Parsed.Success(expectedOne,extraOne)=fastParseExampleMathParsing.expr.parse("1+100")
    expectedOne shouldEqual(101)
    val Parsed.Success(2, _) = fastParseExampleMathParsing.expr.parse("1+1")
    val Parsed.Success(15, _) = fastParseExampleMathParsing.expr.parse("(1+1*2)+3*4")
    val Parsed.Success(21, _) = fastParseExampleMathParsing.expr.parse("((1+1*2)+(3*4*5))/3")
  }

  "Math parsing error handling when 1+1*" should "return error fail index 4" in {
    val Parsed.Failure(expected, failIndex, extra) = fastParseExampleMathParsing.expr.parse("1+1*")
    failIndex shouldEqual(4)
  }
}

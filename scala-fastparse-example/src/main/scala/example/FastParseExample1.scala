package fastParse
/*
 http://www.lihaoyi.com/fastparse/
 */
import fastparse.all._
object fastParseExampleMathParsing { 

  val number: P[Int] = P( CharIn('0'to'9').rep(1).!.map(_.toInt) )
  val addSub: P[Int] = P( divMul ~ (CharIn("+-").! ~/ divMul).rep ).map(eval)
  val parens: P[Int] = P( "(" ~/ addSub ~ ")" )
  val factor: P[Int] = P( number | parens )
  val divMul: P[Int] = P( factor ~ (CharIn("*/").! ~/ factor).rep ).map(eval)
  val expr: P[Int]   = P( addSub ~ End )

// eval function 
  def eval(tree: (Int, Seq[(String, Int)])) = {
    val (base, ops) = tree
    ops.foldLeft(base){ case (left, (op, right)) => op match{
      case "+" => left + right case "-" => left - right
      case "*" => left * right case "/" => left / right
    }}
  }

  val Parsed.Success(2, _) = expr.parse("1+1")
  val Parsed.Success(15, _) = expr.parse("(1+1*2)+3*4")
  val Parsed.Success(21, _) = expr.parse("((1+1*2)+(3*4*5))/3")
  val Parsed.Failure(expected, failIndex, extra) = expr.parse("1+1*")
  assert(expected == (number | parens), failIndex == 4)



 }

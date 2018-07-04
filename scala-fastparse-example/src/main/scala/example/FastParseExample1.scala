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



 }

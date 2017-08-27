package scalaProgramming.oops.chapter10.composition

import scalaProgramming.oops.chapter10.composition.Element.elem


abstract class Element {
  println("Element is initialized")

  def cont: Array[String]

  // these variables must be def otherwise it would be evaluated and it will throw error
  // since the cont is not yet initialized when the class is initialized
  def height = cont.length

  // consider the length of all the string in the element is ssame
  def width = cont(0).length


  def above(that: Element): Element =  {
    elem(that.cont ++ this.cont)
  }

  def beside(that: Element): Element = {
    elem(this.cont ++ that.cont)
  }

  // add padding with respect to w
  // w/2 on left and rest on the right
  def widen(w: Int): Element = {

  this
  }

  def heighten(h: Int): Element = this

  override def toString(): String = {
    cont.mkString("\n")
  }

}

// object to ecapsulate other sub object for Elements
// and create a factory methods to create other elements derived from Element
// this would be the one that's exposed to the Clients to call the ArI
object Element {

  private class UniformElement(ch: Char, override val height: Int, override val width: Int) extends Element {
    private val line: String = ch.toString * width

    override def cont: Array[String] = Array.fill(height)(line)
  }

  private class ArrayElement(arrString: Array[String]) extends Element {
    override def cont: Array[String] = arrString
  }

  private class LineElement(s: String) extends Element {
    override def cont: Array[String] = Array(s)
    override val width = s.length

    override val height = 1
  }

  // factory methods
  // only exposes Element class others could be kept private
  def elem(as: Array[String]): Element = new ArrayElement(as)
  def elem(ch: Char, height: Int, width: Int): Element = new UniformElement(ch, height, width)
  def elem(s:String): Element=new LineElement(s)



  def main(args: Array[String]): Unit = {
    val uniEle= new UniformElement('+', 2, 2)
    val uniEle2= new UniformElement('|', 2,2)
    //println(uniEle)
    println(uniEle above uniEle2)
  }


}
import scala.collection.mutable

val a = Array(1, 2, 1, 0, 0)
val hash = mutable.Map[Int, Int]()
for (key <- a) {
  val counter: Int = hash.getOrElse(key, 0)
  hash.put(key, (counter + 1))
}
hash.filter(_._2 == 1).keys.head

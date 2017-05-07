import com.github.takezoe.solr.scala._

val client = new SolrClient("http://localhost:32769/solr/demo")

// register
// ID must be unique

client
  .add(Map("id" -> "001", "manu" -> "Lenovo", "name" -> "ThinkPad X201s"))
  .add(Map("id" -> "002", "manu" -> "Lenovo", "name" -> "ThinkPad X220"))
  .add(Map("id" -> "003", "manu" -> "Lenovo", "name" -> "ThinkPad X121e"))
  .add(Map("id" -> "004", "manu" -> "Lenovo", "name" -> "yoga 910"))
  .add(Map("id" -> "005", "manu" -> "Lenovo", "name" -> "Yoga 950"))

  .commit
// query
val result = client.query("{!term f=manu}Lenovo")
  .fields("id", "manu", "name")
  .sortBy("id", Order.asc)
  .getResultAsMap(Map("name" -> "ThinkPad"))

result.documents.foreach { doc: Map[String, Any] =>
  println("id: " + doc("id"))
  println("  manu: " + doc("manu"))
  println("  name: " + doc("name"))
}




// query for wslog
val resultWslog = client.query("*:*")
  .fields("id", "servername", "category")
  .sortBy("id", Order.asc)
  .getResultAsMap()

resultWslog.documents.foreach { doc: Map[String, Any] =>
  println(doc)
}

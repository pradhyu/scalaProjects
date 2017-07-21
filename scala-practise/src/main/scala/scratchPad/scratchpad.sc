val responseText =
  """116030455,Clinical.Professional.2016Q1.03.Aetna.Plan.Policy,:FAILED
116030456,Clinical.Professional.2016Q1.03.Aetna.Plan.Policy,:FAILED
916070071,Clinical.Facility.2016Q3.07.Aetna.Plan.Policy,:FAILED
916070072,Clinical.Facility.2016Q3.07.Aetna.Plan.Policy,:FAILED
916070073,Clinical.Facility.2016Q3.07.Aetna.Plan.Policy,:FAILED
916070074,Clinical.Facility.2016Q3.07.Aetna.Plan.Policy,:FAILED
116080043,Clinical.Professional.2016Q3.08.Aetna.Plan.Policy,:FAILED
116080044,Clinical.Professional.2016Q3.08.Aetna.Plan.Policy,:FAILED
116080045,Clinical.Professional.2016Q3.08.Aetna.Plan.Policy,:FAILED
916080024,Clinical.Facility.2016Q3.08.Aetna.Plan.Policy,:FAILED
916080025,Clinical.Facility.2016Q3.08.Aetna.Plan.Policy,:FAILED
916030105,Clinical.Facility.2016Q1.03.Aetna.Plan.Policy,:FAILED
916030106,Clinical.Facility.2016Q1.03.Aetna.Plan.Policy,:FAILED"""


def parseLine(line: String):Tuple2[String,String] = {
  val lineArray = line.split(',')
  val vectorId = lineArray(0)
  val testSetName = lineArray(1)
  Tuple2(testSetName, vectorId)
}
responseText.split("\n").map(parseLine(_)).groupBy(_._1).foreach( record => println(record._2.toSeq))



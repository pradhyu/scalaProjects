import org.apache.commons.codec.binary.Base64;

object HttpBasicAuth {
  val BASIC = "Basic";
  val AUTHORIZATION = "Authorization";

  def encodeCredentials(username: String, password: String): String = {
    new String(Base64.encodeBase64String((username + ":" + password).getBytes));
  };

  def getHeader(username: String, password: String): String =
    BASIC + " " + encodeCredentials(username, password);
};

//Then just add this as a request header.

import scala.io.Source
import java.net.URL

object Main extends App {
  override def main(args: Array[String]) {
    val connection = new URL("https://bamboo.hcinsight.net:8443/bamboo/artifact/RTP-LW7DTQADT177/shared/build-1/QA-html-report/inference-engine/qa/results/run-tests-result-20170516132019/all-vectors-report.txt").openConnection
    val username="i65626"
    val password="True@VerscendL0ve1234"
    connection.setRequestProperty(HttpBasicAuth.AUTHORIZATION, HttpBasicAuth.getHeader(username, password))
    val response = Source.fromInputStream(connection.getInputStream)
    response.getLines().filter(line => line.contains(":FAILED")).foreach(println)

  }
}

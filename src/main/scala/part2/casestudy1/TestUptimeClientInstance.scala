package part2.casestudy1
import scala.concurrent.Future

class TestUptimeClientInstance(hosts: Map[String, Int]) extends TestUptimeClient {
  override def getUptime(hostname: String): Int =
    hosts.getOrElse(hostname, 0)
}

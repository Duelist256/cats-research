package part2.casestudy1

import scala.concurrent.Future

trait RealUptimeClient extends UptimeClient[Future] {
  override def getUptime(hostname: String): Future[Int]
}

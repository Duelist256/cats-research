package part2.casestudy1

import cats.Id

trait TestUptimeClient extends UptimeClient[Id] {
  override def getUptime(hostname: String): Int
}

package part2.casestudy1

import scala.language.higherKinds

trait UptimeClient[F[_]] {
  def getUptime(hostname: String): F[Int]
}

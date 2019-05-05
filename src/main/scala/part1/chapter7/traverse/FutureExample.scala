package part1.chapter7.traverse

object FutureExample {
  import scala.concurrent._
  import scala.concurrent.duration._
  import scala.concurrent.ExecutionContext.Implicits.global

  val hostnames = List(
    "aplha.example.com",
    "beta.example.com",
    "gamma.demo.com"
  )

  def getUptime(hostname: String): Future[Int] =
    Future(hostname.length * 60)


  def main(args: Array[String]): Unit = {
    val allUptimes: Future[List[Int]] =
      hostnames.foldLeft(Future(List.empty[Int])) { (accum, host) =>
        val uptime = getUptime(host)
        for {
          accum <-accum
          uptime <- uptime
        } yield accum :+ uptime
      }
    println(Await.result(allUptimes, 1.second))

    val allUptimes2: Future[List[Int]] =
    Future.traverse(hostnames)(getUptime)
    println(Await.result(allUptimes2, 1.second))

  }
}

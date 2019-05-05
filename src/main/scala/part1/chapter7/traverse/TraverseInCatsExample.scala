package part1.chapter7.traverse

object TraverseInCatsExample {
  def main(args: Array[String]): Unit = {
    import cats.Traverse
    import cats.instances.future._
    import cats.instances.list._
    import scala.concurrent.ExecutionContext.Implicits.global
    import scala.concurrent._
    import scala.concurrent.duration._


    val hostnames = List(
      "aplha.example.com",
      "beta.example.com",
      "gamma.demo.com"
    )

    def getUptime(hostname: String): Future[Int] =
      Future(hostname.length * 60)

    val totalUptime: Future[List[Int]] =
      Traverse[List].traverse(hostnames)(getUptime)

    println(Await.result(totalUptime, 1.second))

    val numbers: List[Future[Int]] = List(Future(1), Future(2), Future(3))
    val numbers2: Future[List[Int]] = Traverse[List].sequence(numbers)
    println(Await.result(numbers2, 1.second))

    import cats.syntax.traverse._
    println(Await.result(hostnames.traverse(getUptime), 1.second))
    println(Await.result(numbers.sequence[Future, Int], 1.second))
  }
}

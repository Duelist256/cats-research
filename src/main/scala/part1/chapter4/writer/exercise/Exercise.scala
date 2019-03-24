package part1.chapter4.writer.exercise


object Exercise {
  def main(args: Array[String]): Unit = {
    import cats.data.Writer
    import cats.instances.vector._
    import cats.syntax.applicative._
    import cats.syntax.writer._

    type Logged[A] = Writer[Vector[String], A]

    def slowly[A](body: => A) =
      try body finally Thread.sleep(100)

    def factorial(n: Int): Writer[Vector[String], Int] = {
      for {
        ans <- slowly(if (n == 0) 1.pure[Logged] else factorial(n - 1).map(_ * n))
        _ <- Vector(s"fact $n $ans").tell
      } yield ans
    }

    println(factorial(5).run)

    import scala.concurrent._
    import scala.concurrent.ExecutionContext.Implicits.global
    import scala.concurrent.duration._

    val future = Future.sequence(Vector(Future(factorial(3)), Future(factorial(3))))
    Await.result(future, 5.seconds).foreach { w =>
      println(w.run)
    }
  }
}
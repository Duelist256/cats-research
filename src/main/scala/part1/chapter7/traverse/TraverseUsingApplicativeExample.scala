package part1.chapter7.traverse

import cats.Applicative
import scala.language.higherKinds

object TraverseUsingApplicativeExample {
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

  import cats.syntax.applicative._
  import cats.syntax.apply._

  def listTraverse[F[_]: Applicative, A, B](list: List[A])(func: A => F[B]): F[List[B]] =
    list.foldLeft(List.empty[B].pure[F]) { (accum, item) =>
      (accum, func(item)).mapN(_ :+ _)
    }

  def listSequence[F[_]: Applicative, B](list: List[F[B]]): F[List[B]] =
    listTraverse(list)(identity)

  def main(args: Array[String]): Unit = {
    import cats.instances.future._

    val totalUptime: Future[List[Int]] =
      listTraverse(hostnames)(getUptime)
    println(Await.result(totalUptime, 1.second))


    // Exercises
    import cats.instances.vector._
    println(listSequence(List(Vector(1, 2), Vector(3, 4))))
    println(listSequence(List(Vector(1, 2), Vector(3, 4), Vector(5, 6))))

    import cats.instances.option._
    def process(inputs: List[Int]): Option[List[Int]] =
      listTraverse(inputs)(n => if (n % 2 == 0) Some(n) else None)

    println(process(List(2, 4, 6)))
    println(process(List(1, 2, 3)))
    println()

    // Validated example
    import cats.data.Validated
    import cats.instances.list._

    type ErrorsOr[A] = Validated[List[String], A]

    def process2(inputs: List[Int]): ErrorsOr[List[Int]] =
      listTraverse(inputs) { n =>
        if (n % 2 == 0) Validated.valid(n)
        else Validated.invalid(List(s"$n is not even"))
      }
    println(process2(List(2, 4, 6)))
    println(process2(List(1, 2, 3)))
  }
}

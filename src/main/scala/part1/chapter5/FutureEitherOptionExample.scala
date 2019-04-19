package part1.chapter5

import cats.data.{EitherT, OptionT}

object FutureEitherOptionExample {
  def main(args: Array[String]): Unit = {
    import cats.syntax.applicative._
    import scala.concurrent.ExecutionContext.Implicits.global
    import cats.instances.future._
    import scala.concurrent.{Await, Future}
    import scala.concurrent.duration._

    type FutureEither[A] = EitherT[Future, String, A]
    type FutureEitherOption[A] = OptionT[FutureEither, A]

    val futureEitherOr: OptionT[FutureEither, Int] =
      for {
        a <- 10.pure[FutureEitherOption]
        b <- 32.pure[FutureEitherOption]
      } yield a + b

    println(futureEitherOr)
    println(futureEitherOr.value)
    val value = futureEitherOr.value.value
    println(value)
    println(Await.result(value, 1.second))

    println()

    type ErrorOr[A] = Either[String, A]
    val errorStack1 = OptionT[ErrorOr, Int](Right(Some(10)))
    println(errorStack1)
    println(errorStack1.value)
  }
}

package part1.chapter5

import cats.data.OptionT

object EitherOptionExample {
  def main(args: Array[String]): Unit = {
    type ErrorOr[A] = Either[String, A]
    type ErrorOrOption[A] = OptionT[ErrorOr, A]

    import cats.syntax.applicative._
    import cats.instances.either._
    val a: ErrorOrOption[Int] = 10.pure[ErrorOrOption]
    val b: ErrorOrOption[Int] = 32.pure[ErrorOrOption]
    val c = a.flatMap(x => b.map(y => x + y))
  }
}

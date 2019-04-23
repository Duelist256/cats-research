package part1.chapter6


object EitherSemigroupal {
  def main(args: Array[String]): Unit = {
    import cats.Monad
    import cats.Semigroupal
    import cats.instances.either._

    type ErrorOr[A] = Either[Vector[String], A]

    val error: ErrorOr[(Nothing, Nothing)] = Semigroupal[ErrorOr].product(
      Left(Vector("Error 1")),
      Left(Vector("Error 2"))
    )
    println(error)
  }
}

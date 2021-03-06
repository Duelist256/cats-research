package part1.chapter4.monaderror

object MonadErrorExamples {
  def main(args: Array[String]): Unit = {
    import cats.MonadError
    import cats.instances.either._

    type ErrorOr[A] = Either[String, A]

    val monadError: MonadError[ErrorOr, String] = MonadError[ErrorOr, String]

    val success: ErrorOr[Int] = monadError.pure(41)
    println(success)

    val failure: ErrorOr[Nothing] = monadError.raiseError("Badness")
    println(failure)

    val result: ErrorOr[ErrorOr[String]] =
      monadError.handleError(failure) {
        case "Badness" =>
          monadError.pure("It's OK ")
        case other =>
          monadError.raiseError("It's not OK")
      }
    println(result)

    val ok: ErrorOr[Int] = monadError.ensure(success)("Number too low!")(_ > 10)
    println(ok)
    val fail: ErrorOr[Int] = monadError.ensure(success)("Number too low!")(_ > 1000)
    println(fail)
  }
}

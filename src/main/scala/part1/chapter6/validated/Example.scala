package part1.chapter6.validated

import scala.util.Try

object Example {
  def main(args: Array[String]): Unit = {
    import cats.Semigroupal
    import cats.data.Validated
    import cats.instances.list._

    type AllErrorsOr[A] = Validated[List[String], A]

    val errors = Semigroupal[AllErrorsOr].product(
      Validated.invalid(List("Error 1")),
      Validated.invalid(List("Error 2"))
    )
    println(errors)
    println()

    val v = Validated.valid(123)
    println(v)
    val i = Validated.Invalid(List("Badness"))
    println(i)

    println()

    val v2 = Validated.valid[List[String], Int](123)
    println(v2)
    val i2 = Validated.invalid[List[String], Int](List("Badness"))
    println(i2)

    println()

    import cats.syntax.validated._
    val v3 = 123.valid[List[String]]
    println(v3)
    val i3 = List("Badness").invalid[Int]
    println(i3)

    println()

    import cats.syntax.applicative._
    import cats.syntax.applicativeError._
    type ErrorsOr[A] = Validated[List[String], A]

    println(123.pure[ErrorsOr])
    println(List("Badness").raiseError[ErrorsOr, Int])
    println()

    println(Validated.catchOnly[NumberFormatException]("foo".toInt))
    println(Validated.catchNonFatal(sys.error("Badness")))
    println(Validated.fromTry(Try("foo".toInt)))
    println(Validated.fromEither[String, Int](Left("Badness")))
    println(Validated.fromOption[String, Int](None, "Badness"))
  }
}

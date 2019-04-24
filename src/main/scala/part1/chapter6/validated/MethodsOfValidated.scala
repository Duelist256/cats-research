package part1.chapter6.validated

import cats.data.Validated

object MethodsOfValidated {
  def main(args: Array[String]): Unit = {
    import cats.syntax.validated._
    val validated: Validated[Nothing, Int] = 123.valid.map(_ * 100)
    println(validated)
    val validated2: Validated[String, Nothing] = 44.invalid.leftMap(_.toString)
    println(validated2)
    val validated3 = 123.valid[String].bimap(_ + "!", _ * 100)
    println(validated3)
    val validated4 = "?".invalid[Int].bimap(_ + "!", _ * 100)
    println(validated4)
    println()

    import cats.syntax.either._
    val invalid: Validated[String, Int] = "Badness".invalid[Int]
    println(invalid)
    val either: Either[String, Int] = "Badness".invalid[Int].toEither
    println(either)
    val invalid2: Validated[String, Int] = "Badness".invalid[Int].toEither.toValidated
    println(invalid2)
    println()

    val validated5: Validated[String, Int] =
      41.valid[String].withEither(_.flatMap(n => Right(n + 1)))
    println(validated5)

    println()
    println(123.valid[String].ensure("Negative")(_ > 0))
    println("fail".invalid[Int].getOrElse(0))
    println("fail".invalid[Int].fold(_ + "!!!", _.toString))
  }
}

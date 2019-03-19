package part1.chapter4.either

import scala.util.Try

object EitherExamples {
  def main(args: Array[String]): Unit = {
    import cats.syntax.either._

    val a = 3.asRight[String]
    val b = 4.asRight[String]

    val result =
      for {
        x <- a
        y <- b
      } yield x*x + y*y
    println(result)

    println(Either.catchOnly[NumberFormatException]("foo".toInt))
    println(Either.catchNonFatal(sys.error("Badness")))
    println(Either.fromTry(Try("foo".toInt)))
    println(Either.fromOption[String, Int](None, "Badness"))
    println("Error".asLeft[Int].getOrElse(0))
    println("Error".asLeft[Int].orElse(2.asRight[String]))

    println(-1.asRight[String].ensure("Must be non-negative")(_ > 0))

    println(
      "error".asLeft[Int].recover {
        case str: String => -1
      }
    )

    println(
      "error".asLeft[Int].recoverWith {
        case str: String => Right(-1)
      }
    )

    println("foo".asLeft[Int].leftMap(_.reverse))
    println(6.asRight[String].bimap(_.reverse, _ * 7))
    println("bar".asLeft[Int].bimap(_.reverse, _ * 7))

    val rightInt: Either[String, Int] = 123.asRight[String]
    println(rightInt)
    val swapped: Either[Int, String] = rightInt.swap
    println(swapped)
  }
}

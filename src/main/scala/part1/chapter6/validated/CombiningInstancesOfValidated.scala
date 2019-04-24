package part1.chapter6.validated

import cats.Semigroupal
import cats.data.Validated

object CombiningInstancesOfValidated {
  def main(args: Array[String]): Unit = {
    type AllErrorsOr[A] = Validated[String, A]
    import cats.instances.string._
    Semigroupal[AllErrorsOr]

    import cats.syntax.validated._
    import cats.syntax.apply._
    val tupled: Validated[String, (Int, Int)] = (
      "Error 1".invalid[Int],
      "Error 2".invalid[Int]
    ).tupled
    println(tupled)

    import cats.instances.vector._
    val tupled2: Validated[Vector[Int], (Int, Int)] = (
      Vector(404).invalid[Int],
      Vector(500).invalid[Int]
    ).tupled
    println(tupled2)

    import cats.data.NonEmptyVector
    val tupled3: Validated[NonEmptyVector[String], (Int, Int)] = (
      NonEmptyVector.of("Error 1").invalid[Int],
      NonEmptyVector.of("Error 2").invalid[Int]
    ).tupled
    println(tupled3)
  }
}

package part2.casestudy3

import cats.data.NonEmptyList
import cats.syntax.validated._
import cats.instances.list._

object Test {

  type Errors = NonEmptyList[String]

  def error(s: String): NonEmptyList[String] = NonEmptyList(s, Nil)

  def longerThan(n: Int): Predicate[Errors, String] =
    Predicate.lift(
      error(s"Must be longer than $n characters"),
      str => str.length > n
    )

  val alphanumeric: Predicate[Errors, String] =
    Predicate.lift(
      error(s"Must be all alphanumeric characters"),
      str => str.forall(_.isLetterOrDigit)
    )

  def contains(ch: Char): Predicate[Errors, String] =
    Predicate.lift(
      error(s"Must contain the character $ch"),
      str => str.contains(ch)
    )

  def containsOnce(ch: Char): Predicate[Errors, String] =
    Predicate.lift(
      error(s"Must contain the character $ch only once"),
      str => str.filter(_ == ch).length == 1
    )

  val atLeast4CharsAndOnlyAlphanumeric: Check[Errors, String, String] =
    Check(Predicate.And(longerThan(3), alphanumeric))


  def main(args: Array[String]): Unit = {
    println(atLeast4CharsAndOnlyAlphanumeric("#"))
    println(atLeast4CharsAndOnlyAlphanumeric("####"))
    println(atLeast4CharsAndOnlyAlphanumeric("f13"))
    println(atLeast4CharsAndOnlyAlphanumeric("f133"))
  }
}

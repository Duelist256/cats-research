package part2.casestudy3

import cats.data.Validated
import cats.data.Validated.{Invalid, Valid}
import cats.kernel.Semigroup
import cats.syntax.apply._
import cats.syntax.semigroup._
import cats.syntax.validated._

sealed trait Predicate[E, A] {
  import Predicate._

  def and(that: Predicate[E, A]): Predicate[E, A] =
    And(this, that)

  def or(that: Predicate[E, A]): Predicate[E, A] =
    Or(this, that)

  def apply(value: A)(implicit s: Semigroup[E]): Validated[E, A] = this match {
    case Pure(func) => func(value)
    case And(left, right) => (left(value), right(value)).mapN((_, _) => value)
    case Or(left, right) => left(value) match {
      case valid @ Valid(_) => valid
      case Invalid(e) =>
        right(value) match {
          case valid @ Valid(_) => valid
          case Invalid(e2) => Invalid(e |+| e2)
        }
    }
  }

}

object Predicate {
  final case class And[E, A](left: Predicate[E, A],
                             right: Predicate[E, A]) extends Predicate[E, A]
  final case class Or[E, A](left: Predicate[E, A],
                            right: Predicate[E, A]) extends Predicate[E, A]
  final case class Pure[E, A](func: A => Validated[E, A]) extends Predicate[E, A]

  def apply[E, A](f: A => Validated[E, A]): Predicate[E, A] = Pure(f)

  def lift[E, A](err: E, fn: A => Boolean): Predicate[E, A] =
    Pure(a => if (fn(a)) a.valid else err.invalid)
}
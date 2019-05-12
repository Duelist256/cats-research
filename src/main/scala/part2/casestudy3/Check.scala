package part2.casestudy3

import cats.data.Validated
import cats.kernel.Semigroup

sealed trait Check[E, A, B] {
  def apply(a: A)(implicit s: Semigroup[E]): Validated[E, B]
  def map[C](func: B => C): Check[E, A, C] = Map[E, A, B, C](this, func)
  def flatMap[C](f: B => Check[E, A, C]): Check[E, A, C] = FlatMap[E, A, B, C](this, f)
  def andThen[C](that: Check[E, B, C]): Check[E, A, C] = AndThen(this, that)
}


final case class Pure[E, A](pred: Predicate[E, A]) extends Check[E, A, A] {
  override def apply(a: A)(implicit s: Semigroup[E]): Validated[E, A] = pred(a)
}

final case class Map[E, A, B, C](check: Check[E, A, B],
                                 func: B => C) extends Check[E, A, C] {
  override def apply(a: A)(implicit s: Semigroup[E]): Validated[E, C] =
    check(a).map(func)
}

final case class FlatMap[E, A, B, C](check: Check[E, A, B],
                                 func: B => Check[E, A, C]) extends Check[E, A, C] {
  override def apply(a: A)(implicit s: Semigroup[E]): Validated[E, C] =
    check(a).withEither(_.flatMap(b => func(b)(a).toEither))
}

final case class AndThen[E, A, B, C](ch: Check[E, A, B],
                                     ch2: Check[E, B, C]) extends Check[E, A, C] {
  override def apply(a: A)(implicit s: Semigroup[E]): Validated[E, C] =
    ch(a).withEither(_.flatMap(b => ch2(b).toEither))
}

object Check {
  def apply[E, A](pred: Predicate[E, A]): Check[E, A, A] = Pure(pred)
}
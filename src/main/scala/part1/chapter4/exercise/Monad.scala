package part1.chapter4.exercise

import scala.language.higherKinds

trait Monad[F[_]] {
  def pure[A](a: A): F[A]
  def flatMap[A, B](fa: F[A])(func: A => F[B]): F[B]

  // Try defining map yourself now
  def map[A, B](fa: F[A])(func: A => B): F[B] =
    flatMap(fa)(a => pure(func(a)))
}

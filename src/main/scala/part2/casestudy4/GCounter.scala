package part2.casestudy4

import cats.Monoid
import cats.instances.list._
import cats.instances.map._
import cats.syntax.semigroup._
import cats.syntax.foldable._

import scala.language.higherKinds

trait GCounter[F[_,_],K, V] {
  def increment(f: F[K, V])(k: K, v: V)(implicit m: Monoid[V]): F[K, V]
  def merge(f1: F[K, V], f2: F[K, V])(implicit b: BoundedSemiLattice[V]): F[K, V]
  def total(f: F[K, V])(implicit m: Monoid[V]): V
}
object GCounter {
  def apply[F[_,_], K, V](implicit counter: GCounter[F, K, V]): GCounter[F, K, V] =
    counter

  implicit def mapGCounter[K, V]: GCounter[Map, K, V] = new GCounter[Map, K, V] {
    override def increment(f: Map[K, V])(k: K, v: V)(implicit m: Monoid[V]): Map[K, V] = {
      val value = f.getOrElse(k, m.empty) |+| v
      f + (k -> value)
    }

    override def merge(f1: Map[K, V], f2: Map[K, V])(implicit b: BoundedSemiLattice[V]): Map[K, V] =
      f1 |+| f2

    override def total(f: Map[K, V])(implicit m: Monoid[V]): V = f.values.toList.combineAll
  }


}
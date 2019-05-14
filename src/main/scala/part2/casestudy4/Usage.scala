package part2.casestudy4

object Usage {

//  import cats.Monoid
//  import cats.syntax.monoid._
//  import cats.syntax.foldable._
//  import cats.instances.list._
//  import KeyValueStore._
//
//  implicit def gcounterInstance[F[_,_], K, V]
//  (implicit kvs: KeyValueStore[F], km: Monoid[F[K, V]]): GCounter[F, K, V] =
//    new GCounter[F, K, V] {
//      def increment(f: F[K, V])(key: K, value: V)
//                   (implicit m: Monoid[V]): F[K, V] = {
//        val total = f.getOrElse(key, m.empty) |+| value
//        f.put(key, total)
//      }
//
//      def merge(f1: F[K, V], f2: F[K, V])
//               (implicit b: BoundedSemiLattice[V]): F[K, V] =
//        f1 |+| f2
//
//      def total(f: F[K, V])(implicit m: Monoid[V]): V =
//        f.values.combineAll
//    }

  def main(args: Array[String]): Unit = {
    import cats.instances.int._
    val g1 = Map("a" -> 7, "b" -> 3)
    val g2 = Map("a" -> 2, "b" -> 5)

    val counter = GCounter[Map, String, Int]
    val merged = counter.merge(g1, g2)
    println(merged)
    val total = counter.total(merged)
    println(total)
  }
}

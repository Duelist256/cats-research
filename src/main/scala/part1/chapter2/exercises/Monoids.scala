package part1.chapter2.exercises

object Monoids {

  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }

  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }

  object Monoid {
    def apply[A](implicit monoid: Monoid[A]): Monoid[A] = monoid
  }

  // The Truth About Monoids

  implicit val andMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  implicit val orMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  implicit val xnorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty = true

    override def combine(a: Boolean, b: Boolean): Boolean = (!a || b) && (a || !b)
  }

  implicit val eorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty = false

    override def combine(a: Boolean, b: Boolean): Boolean = (a && !b) || (!a && b)
  }

  // All Set for Monoids

  implicit def setUnionMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    override def combine(a: Set[A], b: Set[A]): Set[A] = a union b

    override def empty = Set.empty[A]
  }

  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    def empty = 0
    def combine(a: Int, b: Int): Int = a + b
  }

  val intSetMonoid: Monoid[Set[Int]] = Monoid[Set[Int]]

  implicit def setIntersectionSemigroup[A]: Semigroup[Set[A]] = new Semigroup[Set[A]] {
    override def combine(a: Set[A], b: Set[A]): Set[A] = a intersect b
  }

  implicit def symDiffMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty
    override def combine(a: Set[A], b: Set[A]): Set[A] = (a diff b) union (b diff a)
  }

}
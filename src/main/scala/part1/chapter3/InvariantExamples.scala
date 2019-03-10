package part1.chapter3

object InvariantExamples {
  def main(args: Array[String]): Unit = {
    import cats.Monoid
    import cats.instances.string._
    import cats.instances.invariant._
    import cats.syntax.invariant._
    import cats.syntax.semigroup._

    implicit val symbolMonoid: Monoid[Symbol] = Monoid[String].imap(Symbol.apply)(_.name)
    println(Monoid[Symbol].empty)
    println('a |+| 'few |+| 'words)
  }
}

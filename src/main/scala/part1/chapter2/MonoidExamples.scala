package part1.chapter2

object MonoidExamples {
  def main(args: Array[String]): Unit = {
    import cats.Monoid
    import cats.instances.string._
    println(Monoid[String].combine("Hi ", "there"))
    println(Monoid[String].empty)

    import cats.Semigroup
    println(Semigroup[String].combine("Hi ", "there"))


    import cats.instances.int._
    println(Monoid[Int].combine(32, 10))

    import cats.instances.option._

    val a = Option(22)
    val b = Option(20)

    println(Monoid[Option[Int]].combine(a, b))

    // Syntax
    import cats.syntax.semigroup._
    val stringResult = "Hi " |+| "there" |+| Monoid[String].empty
    println(stringResult)
  }
}

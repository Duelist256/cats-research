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

    import cats.instances.map._
    val map1 = Map("a" -> 1, "b" -> 2)
    val map2 = Map("c" -> 3, "d" -> 4)

    println(map1 |+| map2)

    import cats.instances.tuple._
    val tuple1 = ("hello", 123)
    val tuple2 = ("world", 321)
    println(tuple1 |+| tuple2)
  }
}

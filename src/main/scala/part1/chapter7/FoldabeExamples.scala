package part1.chapter7

object FoldabeExamples {
  def main(args: Array[String]): Unit = {
    import cats.Foldable
    import cats.instances.list._

    val ints = List(1, 2, 3)
    val result = Foldable[List].foldLeft(ints, 0)(_ + _)
    println(result)

    import cats.instances.option._
    val maybeInt = Option(123)
    val result2 = Foldable[Option].foldLeft(maybeInt, 10)(_ * _)
    println(result2)

     def bigData = (1 to 100000).toStream
    // This will cause StackOverflowError
    // bigData.foldRight(0L)(_ + _)

    import cats.Eval
    import cats.instances.stream._
    val eval: Eval[Long] =
      Foldable[Stream].foldRight(bigData, Eval.now(0L)) { (num, eval) =>
        eval.map(_ + num)
      }
    println(eval.value)

    println(Foldable[Option].nonEmpty(Option(42)))
    println(Foldable[List].find(List(1, 2, 3))(_ % 2 == 0))

    println()
    // for Monoid[Int]
    import cats.instances.int._
    println(Foldable[List].combineAll(List(1, 2, 3)))
    // foldMap

    // for Monoid[String]
    import cats.instances.string._
    println(Foldable[List].foldMap(List(1, 2, 3))(_.toString))

    // compose Foldables
    import cats.instances.vector._
    val ints2 = List(Vector(1, 2, 3), Vector(4, 5, 6))
    val listVectorFoldable = Foldable[List] compose Foldable[Vector]
    println(listVectorFoldable.combineAll(ints2)) // 21
    println(Foldable[List].combineAll(ints2)) // Vector(1, 2, 3, 4, 5, 6)
  }
}

package part1.chapter3

object SingleArgumentFunctionsAreFunctors {
  def main(args: Array[String]): Unit = {
    val func1: Int => Double = _.toDouble
    val func2: Double => Double = _ * 2
    import cats.instances.function._


    import cats.syntax.functor._
    println((func1 map func2) (1))
    println((func1 andThen func2) (1))
    println(func2(func1(1)))

    val func3: Int => String =
      ((x: Int) => x.toDouble)
      .map(x => x + 1)
      .map(x => x * 2)
      .map(x => x + "!")
    println(func3(123))

    import cats.instances.list._
    List(1) fmap ((i: Int) => i)
  }
}

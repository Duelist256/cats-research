package part1.chapter3.partial_unification

object PartialUnification {
  def main(args: Array[String]): Unit = {
    val func1: Int => Double = (x: Int) => x.toDouble
    val func2: Double => Double = (y: Double) => y * 2

    import cats.Functor
    import cats.instances.function._
    import cats.syntax.functor._
    val func3: Int => Double = func1.map(func2)
    println(func3(10))

    val func3a: Int => Double =
      a => func2(func1(a))

    val func3b: Int => Double = func2.compose(func1)

    // Hypothetical example. Won't compile
    // import cats.syntax.contravariant._
    // val func3c: Int => Double = func2.contramap(func1)

    // some hints to the compiler
    type <=[B, A] = A => B
    type F[A] = Double <= A
    val func2b: Double <= Double = func2
//    val func3c = func2b.contamap(func1)
  }
}

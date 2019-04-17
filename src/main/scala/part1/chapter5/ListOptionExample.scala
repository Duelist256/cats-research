package part1.chapter5


object ListOptionExample {
  def main(args: Array[String]): Unit = {
    import cats.data.OptionT
    type ListOption[A] = OptionT[List, A]

    import cats.instances.list._
    import cats.syntax.applicative._

    val result1: ListOption[Int] = OptionT(List(Option(10), Option(7)))
    val result2: ListOption[Int] = 32.pure[ListOption]
    println(result1)
    println(result2)

    val res =
      result1.flatMap { x: Int =>
        result2.map { y: Int => x + y}
      }
    println(res)

    val sameRes = for {
      x <- result1
      y <- result2
    } yield x + y
    println(sameRes)
  }
}

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
      result1.flatMap { x =>
        result2.map { y => x + y}
      }
    println(res)
  }
}

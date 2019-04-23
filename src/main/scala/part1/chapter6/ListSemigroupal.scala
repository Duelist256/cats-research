package part1.chapter6

object ListSemigroupal {
  def main(args: Array[String]): Unit = {
    import cats.Semigroupal
    import cats.instances.list._

    println(Semigroupal[List].product(List(1, 2), List(3, 4)))
  }
}

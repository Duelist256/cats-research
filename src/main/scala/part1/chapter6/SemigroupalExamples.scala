package part1.chapter6


object SemigroupalExamples {
  def main(args: Array[String]): Unit = {
    import cats.Semigroupal
    import cats.instances.option._

    println(Semigroupal[Option].product(Some(123), Some("abc")))
    println(Semigroupal[Option].product(None, Some("abc")))
    println(Semigroupal[Option].product(Some(123), None))
    println()
    println(Semigroupal.tuple3(Option(1), Option(2), Option(3)))
    println(Semigroupal.tuple3(Option(1), Option(2), Option.empty[Int]))
    println()
    println(Semigroupal.map3(Option(1), Option(2), Option(3))(_ * _ - _))
    println(Semigroupal.map2(Option(1), Option.empty[Int])(_ + _))
  }
}

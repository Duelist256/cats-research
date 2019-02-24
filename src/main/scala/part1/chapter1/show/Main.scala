package part1.chapter1.show

object Main {
  def main(args: Array[String]): Unit = {
    import cats.Show
    import cats.instances.int._
    import cats.instances.string._

    val showInt: Show[Int] = Show.apply[Int]
    val showString: Show[String] = Show.apply[String]

    val intAsString: String = showInt.show(123)
    println(intAsString)

    val stringAsString: String = showString.show("abc")
    println(stringAsString)

    // Using syntax
    import cats.syntax.show._
    val shownInt = 234.show
    println(shownInt)
    val shownString = "def".show
    println(shownString)

    val a = ShowInstances.toStringShow[Double]
    println(a.show(10.2))
  }
}
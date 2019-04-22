package part1.chapter6

object ApplySyntaxExample {
  def main(args: Array[String]): Unit = {
    import cats.instances.option._
    import cats.syntax.apply._
    println((Option(123), Option("abc")).tupled)
    println((Option(123), Option("abc"), Option(true)).tupled)
    println()
    case class Cat(name: String, born: Int, color: String)
    val cat = (Option("Garfield"), Option(1978), Option("Orange & black")).mapN(Cat.apply)
    println(cat)
  }
}

package part1.chapter3.contravariant

object PrintableUsage {
  def main(args: Array[String]): Unit = {
    import part1.chapter3.contravariant.PrintableInstances._

    def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)

    println(format("hello"))
    println(format(true))

    println(format(Box("Hello World!")))
    println(format(Box(true)))
  }
}

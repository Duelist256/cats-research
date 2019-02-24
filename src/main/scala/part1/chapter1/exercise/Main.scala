package part1.chapter1.exercise

import part1.chapter1.Cat

object Main {
  def main(args: Array[String]): Unit = {
    import PrintableInstances._

    // Interface object
    val cat = Cat("Garfield", 38, "ginger and black")
    Printable.print(cat)

    // Interface syntax
    import PrintableSyntax._
    val cat2 = Cat("Tom", 54, "blue")
    cat2.print
  }
}
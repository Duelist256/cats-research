package chapter1.exercise1_3

object Main extends App {
  import PrintableInstances._
  import Printable._

  val cat = Cat("Sam", 4, "grey")

  print(cat)

  import PrintableSyntax._
  cat.print
}

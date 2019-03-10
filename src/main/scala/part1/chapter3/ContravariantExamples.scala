package part1.chapter3


object ContravariantExamples {
  def main(args: Array[String]): Unit = {
    import cats.Contravariant
    import cats.Show
    import cats.instances.string.catsStdShowForString

    val showString = Show[String]

    val showSymbol: Show[Symbol] =
      Contravariant[Show].contramap(showString)((sym: Symbol) => s"'${sym.name}")

    println(showSymbol.show('dave))

    import cats.syntax.contravariant._
    println(showString.contramap[Symbol](_.name).show('dave))
  }
}

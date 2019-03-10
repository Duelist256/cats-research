package part1.chapter3.contravariant

object PrintableInstances {
  implicit val stringPrintable: Printable[String] = new Printable[String] {
    override def format(value: String): String = s""""$value""""
  }

  implicit val booleanPrintable: Printable[Boolean] = new Printable[Boolean] {
    override def format(value: Boolean): String = if (value) "yes" else "no"
  }

  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] =
    p.contramap[Box[A]](_.value)

}

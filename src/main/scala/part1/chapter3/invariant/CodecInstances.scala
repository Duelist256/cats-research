package part1.chapter3.invariant

object CodecInstances {
  implicit val stringCodec: Codec[String] = new Codec[String] {
    override def encode(value: String): String = value
    override def decode(value: String): String = value
  }

  implicit val intCodec: Codec[Int] = stringCodec.imap(_.toInt, _.toString)
  implicit val booleanCodec: Codec[Boolean] = stringCodec.imap(_.toBoolean, _.toString)
  implicit val doubleCodec: Codec[Double] = stringCodec.imap(_.toDouble, _.toString)
  implicit def boxCodec[A](implicit codecA: Codec[A]): Codec[Box[A]] = new Codec[Box[A]] {
    override def encode(box: Box[A]): String = codecA.encode(box.value)
    override def decode(value: String): Box[A] = Box(codecA.decode(value))
  }
}

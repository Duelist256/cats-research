package part1.chapter3.invariant

object CodecUsage {
  def main(args: Array[String]): Unit = {
    import CodecInstances._
    val strFromDouble: String = Codec.encode(2.0D)
    println(strFromDouble)
    val doubleFromStr: Double = Codec.decode[Double]("4.2")
    println(doubleFromStr)

    val strFromIntBox: String = Codec.encode(Box(1234))
    println(strFromIntBox)
    val intBoxFromStr: Box[Int] = Codec.decode[Box[Int]]("14")
    println(intBoxFromStr)
  }
}

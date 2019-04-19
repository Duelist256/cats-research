package part1.chapter5.usage

import cats.data.Writer

import scala.util.Try

object Example {
  def main(args: Array[String]): Unit = {
    type Logged[A] = Writer[List[String], A]

    def parseNumber(str: String): Logged[Option[Int]] =
      Try(str.toInt).toOption match {
        case Some(num) => Writer(List(s"Read $str"), Some(num))
        case None => Writer(List(s"Failed on $str"), None)
      }

    def addAll(a: String, b: String, c: String): Logged[Option[Int]] = {
      import cats.data.OptionT
      import cats.instances.list._

      val result = for {
        a <- OptionT(parseNumber(a))
        b <- OptionT(parseNumber(b))
        c <- OptionT(parseNumber(c))
      } yield a + b + c

      result.value
    }

    val result1 = addAll("1", "2", "3")
    println(result1)
    val result2 = addAll("1", "a", "3")
    println(result2)
  }
}

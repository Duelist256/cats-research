package part1.chapter4.writer

import cats.Id
import cats.data.WriterT

object WriterExample {
  def main(args: Array[String]): Unit = {
    import cats.data.Writer
    val writer: WriterT[Id, Vector[String], Int] = Writer(Vector(
    "It was the best of times",
    "It was the worst of times"
    ), 1859)
    println(writer)


    // If we have a result and no logs
    import cats.instances.vector._
    import cats.syntax.applicative._
    type Logged[A] = Writer[Vector[String], A]
    println(123.pure[Logged])


    // If we have a log and no result
    import cats.syntax.writer._
    println(Vector("msg1", "msg2", "msg3").tell)

    // If we have both a log and a result
    val a = Writer(Vector("msg1", "msg2", "msg3"), 123)
    println(a)
    val b = 123.writer(Vector("msg1", "msg2", "msg3"))
    println(b)

    // extracting logs and results
    println(a.written)
    println(a.value)
    println(a.run)

    // Composing and transforming writers
    val writer1 = for {
      a <- 10.pure[Logged]
      _ <- Vector("a", "b", "c").tell
      b <- 32.writer(Vector("x", "y", "z"))
    } yield a + b
    println(writer1)

    val writer2 = writer1.mapWritten(_.map(_.toUpperCase))
    println(writer2)

    val writer3 = writer1.bimap(
      log => log.map(_.toUpperCase),
      res => res * 100
    )
    println(writer3.run)

    val writer4 = writer1.mapBoth { (log, res) =>
      val log2 = log.map(_ + "!")
      val res2 = res * 1000
      (log2, res2)
    }
    println(writer4.run)

    // reset log
    val writer5 = writer1.reset
    println(writer5.run)

    val writer6 = writer1.swap
    println(writer6.run)
  }
}
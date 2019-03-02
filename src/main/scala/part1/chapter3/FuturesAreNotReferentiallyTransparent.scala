package part1.chapter3

import java.util.Random

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object FuturesAreNotReferentiallyTransparent {
  def main(args: Array[String]): Unit = {
    val future1 = {
      val r = new Random(0L)

      val x = Future(r.nextInt())

      for {
        a <- x
        b <- x
      } yield (a, b)
    }

    val future2 = {
      val r = new Random(0L)

      for {
        a <- Future(r.nextInt())
        b <- Future(r.nextInt())
      } yield (a, b)
    }

    val result1 = Await.result(future1, 1.second)
    val result2 = Await.result(future2, 1.second)

    println(result1)
    println(result2)
  }
}
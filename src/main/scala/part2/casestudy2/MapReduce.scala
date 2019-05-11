package part2.casestudy2

import cats.Monoid
import cats.syntax.monoid._
import cats.syntax.traverse._
import cats.instances.vector._
import cats.instances.future._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global


object MapReduce {
  def foldMap[A, B: Monoid](vector: Vector[A])(f: A => B): B =
    vector.foldLeft(Monoid[B].empty)(_ |+| f(_))

  def parallelFoldMap[A, B: Monoid](vector: Vector[A])(f: A => B): Future[B] = {
    val n = Runtime.getRuntime.availableProcessors()
    vector
      .grouped(n).toVector                  // divide into batches
      .map(vec => Future(foldMap(vec)(f)))  // process each batches in parallel
      .sequence                             // List[Future[B]] => Future[List[B]]
      .map(foldMap(_)(identity))            // reduce batches
  }


  def main(args: Array[String]): Unit = {
    // single thread foldMap
    import cats.instances.int._
    println(foldMap(Vector(1, 2, 3))(identity))
    import cats.instances.string._
    println(foldMap(Vector(1, 2, 3))(_.toString + "! "))
    println(foldMap("Hello world!".toVector)(_.toString.toUpperCase()))

    println()

    // parallelFoldMap
    import scala.concurrent.duration._
    val res = parallelFoldMap("Hello world!".toVector)(_.toString.toUpperCase())
    println(Await.result(res, 1.second))
    val res2 = parallelFoldMap(Vector(1, 2, 3))(_.toString + "! ")
    println(Await.result(res2, 1.second))
    val res3 = parallelFoldMap(Vector(1, 2, 3))(identity)
    println(Await.result(res3, 1.second))
  }
}
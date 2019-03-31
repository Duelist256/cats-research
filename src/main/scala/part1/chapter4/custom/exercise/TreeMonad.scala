package part1.chapter4.custom.exercise

import cats.Monad

import scala.annotation.tailrec

object TreeMonad {

  implicit val treeMonad: Monad[Tree] = new Monad[Tree] {
    override def flatMap[A, B](fa: Tree[A])(f: A => Tree[B]): Tree[B] = fa match {
      case Branch(left, right) => Branch(flatMap(left)(f), flatMap(right)(f))
      case Leaf(value) => f(value)
    }

    override def tailRecM[A, B](a: A)(f: A => Tree[Either[A, B]]): Tree[B] =
      f(a) match {
        case Branch(left, right) =>
          Branch(
            flatMap(right) {
              case Right(value) => pure(value)
              case Left(value) => tailRecM(value)(f)
            },
            flatMap(right) {
              case Right(value) => pure(value)
              case Left(value) => tailRecM(value)(f)
            }
          )
        case Leaf(value) =>
          value match {
            case Left(v) => tailRecM(v)(f)
            case Right(v) => Leaf(v)
          }
      }

    override def pure[A](x: A): Tree[A] = Leaf(x)
  }



  def main(args: Array[String]): Unit = {
    import cats.syntax.functor._
    import cats.syntax.flatMap._
    import Tree._

    val result: Tree[Int] = branch(leaf(100), leaf(200)).
      flatMap(x => branch(leaf(x - 1), leaf(x + 1)))
    println(result)

    val result2 = for {
      a <- branch(leaf(100), leaf(200))
      b <- branch(leaf(a - 10), leaf(a + 10))
      c <- branch(leaf(b - 1), leaf(b + 1))
    } yield c

    println(result2)
  }
}
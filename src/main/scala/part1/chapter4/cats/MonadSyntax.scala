package part1.chapter4.cats

import cats.Id

object MonadSyntax {
  def main(args: Array[String]): Unit = {
    import cats.instances.option._
    import cats.instances.list._
    import cats.syntax.applicative._

    val option = 1.pure[Option]
    println(option)

    val list = 1.pure[List]
    println(list)

    import cats.Monad
    import cats.syntax.functor._
    import cats.syntax.flatMap._
    import scala.language.higherKinds

    def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
      for {
        x <- a
        y <- b
      } yield x * x + y * y

    println(sumSquare(Option(3), Option(4)))
    println(sumSquare(List(1, 2, 3), List(4, 5)))


    // The Identity Monad
    println(sumSquare(4: Id[Int], 3: Id[Int]))

    val a = Monad[Id].pure(3)
    println(a)
    val b = Monad[Id].flatMap(a)(_ + 1)
    println(b)

    import cats.syntax.functor._
    import cats.syntax.flatMap._
    val result: Id[Int] = for {
      x <- a
      y <- b
    } yield x + y
    println(result)
  }
}

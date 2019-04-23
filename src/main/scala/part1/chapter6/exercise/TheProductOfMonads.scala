package part1.chapter6.exercise

object TheProductOfMonads {

  import cats.Monad
  import scala.language.higherKinds
  import cats.syntax.flatMap._
  import cats.syntax.functor._

  def product[M[_]: Monad, A, B](x: M[A], y: M[B]): M[(A, B)] =
    x.flatMap(a => y.map(b => (a, b)))

  def main(args: Array[String]): Unit = {

  }
}

package part1.chapter4.custom

object DefiningCustomMonads {
  def main(args: Array[String]): Unit = {
    import cats.Monad
    import scala.annotation.tailrec

    val optionMonad = new Monad[Option] {
      override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = fa.flatMap(f)

      @tailrec
      override def tailRecM[A, B](a: A)(f: A => Option[Either[A, B]]): Option[B] =
        f(a) match {
          case None => None
          case Some(Right(value)) => Some(value)
          case Some(Left(value)) => tailRecM(value)(f)
        }

      override def pure[A](x: A): Option[A] = Option(x)
    }
  }
}
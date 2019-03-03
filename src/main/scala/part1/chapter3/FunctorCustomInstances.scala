package part1.chapter3

import cats.Functor

import scala.concurrent.{ExecutionContext, Future}

object FunctorCustomInstances {
  def main(args: Array[String]): Unit = {

    implicit val unit: Functor[Option] = new Functor[Option] {
      override def map[A, B](option: Option[A])(f: A => B): Option[B] = option.map(f)
    }

    implicit def futureFunctor(implicit ec: ExecutionContext): Functor[Future] =
      new Functor[Future] {
        override def map[A, B](future: Future[A])(f: A => B): Future[B] = future.map(f)
      }
  }
}

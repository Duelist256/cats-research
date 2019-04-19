package part1.chapter5

import cats.data.EitherT

import scala.concurrent.Future

package object usage {
  sealed trait HttpError
  final case class NotFound(item: String) extends HttpError
  final case class BadRequest(msg: String) extends HttpError

  type FutureEither[A] = EitherT[Future, HttpError, A]
}

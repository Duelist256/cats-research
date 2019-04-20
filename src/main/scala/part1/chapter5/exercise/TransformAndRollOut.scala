package part1.chapter5.exercise

import cats.data.EitherT

import scala.concurrent.{Await, Future}
import cats.instances.future._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object TransformAndRollOut {

  type Response[A] = EitherT[Future, String, A]

  val powerLevels: Map[String, Int] = Map(
    "Jazz" -> 6,
    "Bumblebee" -> 8,
    "Hot Rod" -> 10
  )

  def getPowerLevel(autobot: String): Response[Int] = powerLevels.get(autobot) match {
    case Some(value) => EitherT.right(Future(value))
    case None => EitherT.left(Future(s"$autobot unreachable"))
  }

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] =
    for {
      a <- getPowerLevel(ally1)
      b <- getPowerLevel(ally2)
    } yield (a + b) > 15

  def tacticalReport(ally1: String, ally2: String): String = {
    val f = canSpecialMove(ally1, ally2).value.map {
      case Right(true) => s"$ally1 and $ally2 are ready to roll out"
      case Right(false) => s"$ally1 and $ally2 need a recharge"
      case Left(e) => s"Comms error: $e"
    }
    Await.result(f, 1.second)
  }


  def main(args: Array[String]): Unit = {
    println(tacticalReport("Jazz", "Bumblebee"))
    println(tacticalReport("Bumblebee", "Hot Rod"))
    println(tacticalReport("Jazz", "Ironhide"))
  }
}

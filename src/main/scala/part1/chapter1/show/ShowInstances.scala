package part1.chapter1.show

import java.util.Date

import cats.Show

object ShowInstances {
  implicit val dateShow: Show[Date] = Show.show(t => s"${t.getTime}ms since the epoch.")
  implicit def toStringShow[A]: Show[A] = Show.fromToString[A]
}
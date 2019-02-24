package part1.chapter1.show

import java.util.Date

import cats.Show
import part1.chapter1.Cat
import cats.implicits.{catsStdShowForInt, catsStdShowForString, toShow}

object ShowInstances {
  implicit val dateShow: Show[Date] = Show.show(t => s"${t.getTime}ms since the epoch.")
  implicit def toStringShow[A]: Show[A] = Show.fromToString[A]

  implicit val catShow: Show[Cat] = Show.show(cat => {
    val name: String = cat.name.show
    val age = cat.age.show
    val color = cat.color.show
    s"$name is a $age years old $color cat"
  })
}
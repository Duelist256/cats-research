package part1.chapter1.eq

import java.util.Date

import cats.Eq
import cats.instances.long._
import cats.instances.int._
import cats.instances.string._
import cats.syntax.eq._
import part1.chapter1.Cat

object EqInstances {
  implicit val dateEq: Eq[Date] =
    Eq.instance[Date] { (date1, date2) =>
      date1.getTime === date2.getTime
    }

  implicit val catEq: Eq[Cat] = Eq.instance[Cat] { (cat1, cat2) =>
    (cat1.name === cat2.name) &&
    (cat1.age === cat2.age) &&
    (cat1.color === cat2.color)
  }
}
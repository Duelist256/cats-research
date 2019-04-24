package part1.chapter6.validated.exercise

import cats.data.Validated
import cats.syntax.either._
import cats.syntax.apply._
import cats.instances.list._

object FormValidation {

  type FormData = Map[String, String]

  type FailSlow[A] = Validated[List[String], A]
  type FailFast[A] = Either[List[String], A]

  def readUserV(map: FormData): FailSlow[User] =
    (
      readName(map).toValidated,
      readAge(map).toValidated
    ).mapN((name, age) => User(name, age))

  def readUser(map: FormData): FailFast[User] =
    for {
      name <- readName(map)
      age <- readAge(map)
    } yield User(name, age)

  def readName(map: FormData): FailFast[String] = {
    val fieldName = "name"
    for {
      v <- getValue(fieldName, map)
      s <- nonBlank(fieldName)(v)
    } yield s
  }

  def readAge(map: FormData): FailFast[Int] = {
    val fieldName = "age"
    for {
      v <- getValue(fieldName, map)
      i <- parseInt(fieldName)(v)
      n <- nonNegative(fieldName)(i)
    } yield n
  }

  def getValue(fieldName:String, map: FormData): FailFast[String] =
    map.get(fieldName).toRight(List(s"$fieldName field not specified!"))

  def parseInt(fieldName: String)(value: String): FailFast[Int] =
    Either.catchOnly[NumberFormatException](value.toInt)
      .leftMap(_ => List(s"$fieldName must me an integer"))

  def nonBlank(fieldName: String)(value: String): FailFast[String] =
    value.asRight.ensure(List(s"$fieldName must not be blank!"))(_.nonEmpty)

  def nonNegative(fieldName: String)(value: Int): FailFast[Int] =
    value.asRight.ensure(List(s"$fieldName must be non negative!"))(_ >= 0)

  def main(args: Array[String]): Unit = {
    println(readName(Map("name" -> "Dade Murphy")))
    println(readName(Map("name" -> "")))
    println(readName(Map()))
    println(readAge(Map("age" -> "11")))
    println(readAge(Map("age" -> "-1")))
    println(readAge(Map()))
    println()
    println(readUser(Map("name" -> "Dade Murphy", "age" -> "11")))
    println(readUser(Map("name" -> "Dade Murphy")))
    println(readUser(Map("name" -> "", "age" -> "-11")))
    println()
    println(readUserV(Map("name" -> "Dade Murphy", "age" -> "11")))
    println(readUserV(Map("name" -> "Dade Murphy")))
    println(readUserV(Map("name" -> "", "age" -> "-11")))
    println(readUserV(Map("name" -> "Lel", "age" -> "-11")))
    println(readUserV(Map()))
  }
}

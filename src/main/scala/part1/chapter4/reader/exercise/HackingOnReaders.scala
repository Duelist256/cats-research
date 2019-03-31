package part1.chapter4.reader.exercise

import cats.data.Reader
import cats.syntax.applicative._

object HackingOnReaders {

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(_.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader(_.passwords.get(username).contains(password))

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    for {
      usernameOpt <- findUsername(userId)
      loggedIn <-usernameOpt
                .map(username => checkPassword(username, password))
                .getOrElse(false.pure[DbReader])
    } yield loggedIn

  def main(args: Array[String]): Unit = {
    val users = Map(
      1 -> "dade",
      2 -> "kate",
      3 -> "margo"
    )
    val passwords = Map(
      "dade" -> "zerocool",
      "kate" -> "acidburn",
      "margo" -> "secret"
    )

    val db = Db(users, passwords)

    println(checkLogin(1, "zerocool").run(db))
    println(checkLogin(4, "davinci").run(db))
  }
}
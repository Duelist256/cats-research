package part1.chapter4.either

object ErrorHandling {

  sealed trait LoginError extends Product with Serializable
  final case class UserNotFound(username: String) extends LoginError
  final case class PasswordIncorrect(username: String) extends LoginError
  case object UnexpectedError extends LoginError

  case class User(username: String, password: String)
  type LoginResult = Either[LoginError, User]

  def main(args: Array[String]): Unit = {
    def handleError(error: LoginError): Unit = error match {
      case UserNotFound(u) => println(s"User not found: $u")
      case PasswordIncorrect(u) => println(s"Password incorrect: $u")
      case UnexpectedError => println("Unexpected error")
    }

    import cats.syntax.either._

    val result1: LoginResult = User("dave", "passw0rd").asRight
    val result2: LoginResult = UserNotFound("dave").asLeft

    result1.fold(handleError, println)
    result2.fold(handleError, println)
  }
}

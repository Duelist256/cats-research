package part1.chapter6


object FutureSemigroupal {
  def main(args: Array[String]): Unit = {
    import cats.Semigroupal
    import cats.instances.future._
    import scala.concurrent.{Await, Future}
    import scala.concurrent.ExecutionContext.Implicits.global
    import scala.concurrent.duration._

    val futurePair = Semigroupal[Future].product(Future("Hello"), Future(123))
    println(Await.result(futurePair, 1.second))

    import cats.syntax.apply._
    case class Cat(name: String, yearOfBirth: Int, favoriteFoods: List[String])
    val futureCat = (
      Future("Garfield"),
      Future(1978),
      Future(List("Lasagne"))
    ).mapN(Cat.apply)
    println(Await.result(futureCat, 1.second))
  }
}

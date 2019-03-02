package part1.chapter3

object FunctorTypeclassExamples {
  def main(args: Array[String]): Unit = {

    import cats.Functor
    import cats.instances.list._
    import cats.instances.option._

    val list1 = List(1, 2, 3)
    val list2 = Functor[List].map(list1)(_ * 2)
    println(list2)


    val option = Option(123)
    val option2 = Functor[Option].map(option)(_ * 2).map(_.toString)
    println(option2)

    // lift Example
    val func: Int => Int = (x: Int) => x + 1
    val liftedFunc: Option[Int] => Option[Int] = Functor[Option].lift(func)
    println(liftedFunc(Option(1)))

    // Functor Syntax
    import cats.syntax.functor._
    import scala.language.higherKinds
    def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
      start.map(n => (n + 1) * 2)

    println(doMath(Option(20)))
    println(doMath(List(1, 2, 3)))

    final case class Box[A](value: A)
    val box = Box[Int](123)
  }
}

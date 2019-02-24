package part1.chapter1.eq

object Main {
  def main(args: Array[String]): Unit = {
    import cats.syntax.eq._
    import cats.instances.int._
    import cats.instances.option._

    val a: Option[Int] = Some(1)
    val b: Option[Int] = Some(2)
    println(a === b)

    val a2: Option[Int] = Some(1)
    val b2: Some[Int] = Some(2)
    println(a2 === b2)

    val a3: Some[Int] = Some(1)
    val b3: Option[Int] = Some(2)
//    println(a3 === b3) // there is no instance of type Eq[Some[Int]]
  }
}
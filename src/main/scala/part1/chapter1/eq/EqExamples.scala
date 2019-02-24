package part1.chapter1.eq

import part1.chapter1.Cat

object EqExamples {
  def main(args: Array[String]): Unit = {
    import cats.Eq
    import cats.instances.int._
    val eqInt: Eq[Int] = Eq[Int]
    println(eqInt.eqv(123, 123))
    println(eqInt.eqv(123, 234))

    import cats.syntax.eq._
    println(123 === 123)
    println(123 =!= 234)
    // 123 === "234"
    // error: type mismatch

    // Eq[Option[_]] examples
    import cats.instances.option._
    println(Option(1) === Option.empty[Int])

    import cats.syntax.option._
    println(1.some === none[Int])
    println(1.some =!= none[Int])

    println("::: Cat comparison :::")
    import EqInstances.catEq
    val cat1 = Cat("Garfield", 38, "orange and black")
    val cat2 = Cat("Heathcliff", 32, "orange and black")
    println(cat1 === cat2)
    println(cat1 =!= cat2)

    val a: Option[Int] = Some(1)
    val b: Some[Int] = Some(2)

    println(a === b)

    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]
    println(optionCat1 === optionCat2)
    println(optionCat1 =!= optionCat2)
  }
}
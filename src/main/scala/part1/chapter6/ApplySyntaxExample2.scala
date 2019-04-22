package part1.chapter6

object ApplySyntaxExample2 {
  def main(args: Array[String]): Unit = {
    import cats.Monoid
    import cats.syntax.apply._

    case class Cat(name: String, yearOfBirth: Int, favoriteFoods: List[String])

    val tupleToCat: (String, Int, List[String]) => Cat = Cat.apply
    val catToTuple: Cat => (String, Int, List[String]) =
      cat => (cat.name, cat.yearOfBirth, cat.favoriteFoods)


    // imported in such way because of Semigroupal[Monoid]
    import cats.instances.all._
    implicit val catMonoid: Monoid[Cat] = (
      Monoid[String],
      Monoid[Int],
      Monoid[List[String]]
    ).imapN(tupleToCat)(catToTuple)

    import cats.syntax.semigroup._
    val garfield = Cat("Garfield", 1978, List("Lasagne"))
    val heathcliff = Cat("Heathcliff", 1988, List("Junk Food"))
    println(garfield |+| heathcliff)
  }
}

package part1.chapter4.reader

object Example {
  def main(args: Array[String]): Unit = {
    import cats.data.Reader

    case class Cat(name: String, favoriteFood: String)

    val catName: Reader[Cat, String] = Reader(cat => cat.name)

    println(catName.run(Cat("Garfield", "lasagne")))

    // map extends the computation in the Reader by passing it's result through a function
    val greetKitty: Reader[Cat, String] = catName.map(name => s"Hello $name")

    val heathcliff = Cat("Heathcliff", "junk food")
    println(greetKitty.run(heathcliff))

    // flatMap allows us to combine readers that depend on the same input type
    val feedKitty: Reader[Cat, String] =
      Reader(cat => s"Have a nice bowl of ${cat.favoriteFood}")

    val greetAndFeed: Reader[Cat, String] =
      greetKitty
        .flatMap(greet =>
          feedKitty
            .map(feed => s"$greet. $feed.")
        )

    println(greetAndFeed.run(heathcliff))
  }
}
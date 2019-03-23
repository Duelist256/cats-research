package part1.chapter4.eval

object TypesOfEvaluation {
  def main(args: Array[String]): Unit = {
    // eager and memoized
    val x = {
      println("Computing X")
      math.random()
    }
    println("Accessing X ...")
    println(x) // first access
    println(x) // second access

    // lazy and not memoized
    def y = {
      println("Computing Y")
      math.random()
    }
    println("Accessing Y ...")
    println(y)
    println(y)

    // lazy and memoized
    lazy val z = {
      println("Computing Z")
      math.random()
    }
    println("Accessing Z ...")
    println(z)
    println(z)
  }
}

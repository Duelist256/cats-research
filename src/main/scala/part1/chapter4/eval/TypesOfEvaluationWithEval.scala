package part1.chapter4.eval

object TypesOfEvaluationWithEval {
  def main(args: Array[String]): Unit = {
    import cats.Eval

    // eager and memoized
    val xEval = Eval.now {
      println("Computing X")
      math.random()
    }
    println("Accessing X ...")
    println(xEval.value) // first access
    println(xEval.value) // second access

    println()

    // lazy and not memoized
    def yEval = Eval.always {
      println("Computing Y")
      math.random()
    }
    println("Accessing Y ...")
    println(yEval.value)
    println(yEval.value)

    println()

    // lazy and memoized
    lazy val zEval = Eval.later {
      println("Computing Z")
      math.random()
    }
    println("Accessing Z ...")
    println(zEval.value)
    println(zEval.value)
  }
}

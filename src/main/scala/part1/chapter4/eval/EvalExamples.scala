package part1.chapter4.eval

import cats.Eval

object EvalExamples {
  def main(args: Array[String]): Unit = {
    val greeting = Eval.
      always { println("Step 1"); "Hello" }.
      map { str => println("Step 2"); s"$str world" }
    println("Accessing greeting ...")
    println(greeting.value)

    println()

    val ans = for {
      a <- Eval.now { println("Calculating A"); 40 }
      b <- Eval.always { println("Calculating B"); 2 }
    } yield {
      println("Adding ")
      a + b
    }
    println("Accessing ans ...")
    println(ans.value) // mapping functions are always called lazily on demand (def)
    println(ans.value)

    println()

    // memoize
    val saying = Eval.
      always { println("Step 1"); "The cat" }.
      map { str => println("Step 2"); s"$str sat on" }.
      memoize.
      map { str => println("Step 3"); s"$str the mat" }
    println("Accessing saying ...")
    println(saying.value)
    println(saying.value)
  }
}

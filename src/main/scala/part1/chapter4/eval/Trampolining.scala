package part1.chapter4.eval

import cats.Eval

object Trampolining {
  def main(args: Array[String]): Unit = {
    def factorial(n: BigInt): BigInt =
      if (n == 1) n else n * factorial(n - 1)
//    Causes java.lang.StackOverflowError
//    factorial(50000)

    def factorial2(n: BigInt): Eval[BigInt] =
      if (n == 1) {
        Eval.now(n)
      } else {
        factorial2(n - 1).map(_ * n)
      }

//    factorial2(50000).value // still stack unsafe, causes java.lang.StackOverflowError

    def stackSafeFactorial(n: BigInt): Eval[BigInt] =
      if (n == 1) {
        Eval.now(n)
      } else {
        Eval.defer(stackSafeFactorial(n - 1)).map(_ * n)
      }

    println(stackSafeFactorial(500000).value)
    // Trampolining avoids consuming stack by creating a chain of function objects
    // on the heap
  }
}

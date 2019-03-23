package part1.chapter4.eval.exercise

import cats.Eval

object SaferFoldingUsingEval {
  def main(args: Array[String]): Unit = {
    //  Stack unsafe foldRight, causes  java.lang.StackOverflowError
    def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
      as match {
        case head :: tail =>
          fn(head, foldRight(tail, acc)(fn))
        case Nil =>
          acc
      }

//        foldRight(List.range[Int](1, 10000), 1)((v, acc) => v * acc)

    def foldRightSafe[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = {
      def foldR(as: List[A], acc: B)(fn: (A, B) => B): Eval[B] =
        as match {
          case head :: tail =>
          Eval.defer(foldR(tail, acc)(fn)).map(fn(head, _))
          case Nil =>
          Eval.now(acc)
        }
      foldR(as, acc)(fn).value
    }

    println(foldRightSafe(List.range[BigInt](1, 10000), BigInt(1))((v, acc) => v * acc))
  }
}

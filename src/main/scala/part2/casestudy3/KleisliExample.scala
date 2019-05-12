package part2.casestudy3

import cats.data.Kleisli

object KleisliExample {
  def main(args: Array[String]): Unit = {
    val step1: Kleisli[List, Int, Int] =
      Kleisli(x => List(x + 1, x - 1)) // 21 19

    val step2: Kleisli[List, Int, Int] =
      Kleisli(x => List(x, -x)) // 21 -21 19 - 19

    val step3: Kleisli[List, Int, Int] =
      Kleisli(x => List(x * 2, x / 2))


    import cats.instances.list._
    val pipeline = step1 andThen step2 andThen step3
    println(pipeline.run(20))
  }
}

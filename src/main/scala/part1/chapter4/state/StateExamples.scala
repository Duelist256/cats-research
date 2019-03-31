package part1.chapter4.state

object StateExamples {
  def main(args: Array[String]): Unit = {
    import cats.data.State

    val a = State[Int, String] { state =>
      (state, s"The state is $state")
    }

    // get the state and the result
    println(a.run(10).value)


    // get the state, ignore the result
    println(a.runS(10).value)


    // ignore the state, get the result
    println(a.runA(10).value)


    val step1 = State[Int, String] { num =>
      val ans = num + 1
      (ans, s"Result of step1: $ans")
    }

    val step2 = State[Int, String] { num =>
      val ans = num * 2
      (ans, s"Result of step1: $ans")
    }

    val both = for {
      a <- step1
      b <- step2
    } yield (a, b)

    println(both.run(10).value)
    println()

    val getDemo = State.get[Int]
    println(getDemo.run(20).value)
    val setDemo = State.set[Int](30)
    println(setDemo.run(10).value)
    val pureDemo = State.pure[Int, String]("Result")
    println(pureDemo.run(10).value)
    val inspectDemo = State.inspect[Int, String](_ + "!")
    println(inspectDemo.run(10).value)
    val modifyDemo = State.modify[Int](_ + 1)
    println(modifyDemo.run(10).value)
    println()

    import State._
    val program: State[Int, (Int, Int, Int)] = for {
      a <- get[Int]
      _ <- set[Int](a + 1)
      b <- get[Int]
      _ <- modify[Int](_ + 1)
      c <- inspect[Int, Int](_ * 1000)
    } yield (a, b, c)
    println(program.run(1).value)
  }
}
package part1.chapter4.state.exercise

import cats.data.State

object PostOrderCalculator {

  type CalcState[A] = State[List[Int], A]


  def operator(function: (Int, Int) => Int): CalcState[Int] = State[List[Int], Int]{
    case a :: b :: tail =>
      val res = function(a, b)
      (res :: tail, res)
    case _ => sys.error("Fail!")
  }

  def operand(x: Int): CalcState[Int] = State[List[Int], Int]{ list =>
    (x :: list, x)
  }


  def evalOne(sym: String): CalcState[Int] =
    sym match {
      case "+" => operator(_ + _)
      case "-" => operator(_ - _)
      case "*" => operator(_ * _)
      case "/" => operator(_ / _)
      case _ => operand(sym.toInt)
    }

  import cats.syntax.applicative._
  def evalAll(input: List[String]): CalcState[Int] =
    input.foldLeft(0.pure[CalcState]) { (a, b) =>
      a.flatMap(_ => evalOne(b))
    }


  def evalInput(input: String): Int =
    evalAll(input.split(" ").toList).runA(Nil).value



  def main(args: Array[String]): Unit = {
    println(evalOne("42").runA(Nil).value)

    val program = for {
      _ <- evalOne("1")
      _ <- evalOne("2")
      ans <- evalOne("+")
    } yield ans
    println(program.runA(Nil).value)

    val program2 = evalAll(List("1", "2", "+", "3", "*"))
    println(program2.runA(Nil).value)

    val program3 = for {
      _ <- evalAll(List("1", "2", "+"))
      _ <- evalAll(List("3", "4", "+"))
      ans <- evalOne("*")
    } yield ans
    println(program3.runA(Nil).value)


    println(evalInput("3 4 +"))
    println(evalInput("3 4 *"))
    println(evalInput("25 125 /"))
  }
}
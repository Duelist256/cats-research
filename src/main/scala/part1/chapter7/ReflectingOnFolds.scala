package part1.chapter7

object ReflectingOnFolds {
  def main(args: Array[String]): Unit = {
    val result1 = List(1, 2, 3).foldLeft(List.empty[Int])((acc, v) => v :: acc)
    val result2 = List(1, 2, 3).foldRight(List.empty[Int])((v, acc) => v :: acc )
    println(result1)
    println(result2)
  }
}

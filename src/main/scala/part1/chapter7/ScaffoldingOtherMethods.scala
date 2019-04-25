package part1.chapter7

object ScaffoldingOtherMethods {

  def map[A, B](list: List[A])(f: A => B): List[B] =
    list.foldRight(List.empty[B])((v, acc) => f(v) :: acc)

  def flatMap[A, B](list: List[A])(f: A => List[B]): List[B] =
    list.foldRight(List.empty[B])((v, acc) => f(v) ++ acc)

  def filter[A](list: List[A])(f: A => Boolean): List[A] =
    list.foldRight(List.empty[A]) { (v, acc) =>
      if (f(v)) v :: acc
      else acc
    }

  def sum(list: List[Int]): Int = list.foldRight(0)(_ + _)

  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3)

    println(map(list)(_ * 2))
    println(flatMap(list)(v => List(v * 3)))
    println(filter(list)(v => v % 2 == 1))
    println(sum(list))
  }
}

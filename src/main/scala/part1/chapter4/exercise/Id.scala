package part1.chapter4.exercise

object Id {
  type Id[A] = A

  def pure[A](a: A): Id[A] = a
  def map[A, B](fa: Id[A])(func: A => B): Id[B] = func(fa)
  def flatMap[A, B](fa: Id[A])(func: A => Id[B]): Id[B] = func(fa)

  def main(args: Array[String]): Unit = {
    val a: Id[Int] = 4

    println(pure(a))
    println(flatMap(a)(a => a * 10))
    println(map(a)(a => a * 20))
  }
}

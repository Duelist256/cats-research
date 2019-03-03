package part1.chapter3.exercises

import cats.Functor

object BranchingOutWithFunctors {

  sealed trait Tree[+A]
  final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
  final case class Leaf[A](left: A) extends Tree[A]

  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
      case Leaf(a) => Leaf(f(a))
      case Branch(first, second) => Branch(map(first)(f), map(second)(f))
    }
  }

  def main(args: Array[String]): Unit = {
    val tree: Tree[Int] = Branch(
      Branch(Leaf(1), Leaf(2)),
      Branch(Leaf(3), Leaf(4))
    )

    import cats.syntax.functor._


    val expectedTree: Tree[Int] = Branch(
      Branch(Leaf(2), Leaf(3)),
      Branch(Leaf(4), Leaf(5))
    )

    val result = tree map (_ + 1)
    println(result)
    assert(result == expectedTree)

    val expectedTree2: Tree[Int] = Branch(
        Branch(Leaf(11), Leaf(21)),
        Branch(Leaf(31), Leaf(41))
    )

    val result2: Tree[Int] = tree map (_ + "1") map (_.toInt)
    println(result2)
    assert(result2 == expectedTree2)
  }
}

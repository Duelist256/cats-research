package part1.chapter7

object FoldabeSyntax {
  def main(args: Array[String]): Unit = {
    import cats.syntax.foldable._
    import cats.instances.list._
    import cats.instances.int._
    import cats.instances.string._

    println(List(1, 2, 3).combineAll)
    println(List(1, 2, 3).foldMap(_.toString))
  }
}

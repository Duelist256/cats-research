package part1.chapter2.exercises

import cats.Monoid
import cats.instances.int._
import cats.instances.option._

object SuperAdder {

  case class Order(totalCost: Double, quantity: Double)

  def add[A : Monoid](items: List[A]): A = {
    val monoid = implicitly[Monoid[A]]
    items.foldLeft(monoid.empty)(monoid.combine)
  }

  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    override def empty: Order = Order(0, 0)
    override def combine(x: Order, y: Order): Order =
      Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
  }


  def main(args: Array[String]): Unit = {
    println(add(List.empty[Int]))
    println(add(List(1, 2, 3)))
    println(add(List(1, 2, 3)))
    println(add(List(Option(1), Option(2), Option(3))))
    println(add(List(None: Option[Int])))
    println(add(List(None, Some(1), None, Some(3))))
    println(add(List(None, Some(1), None, Some(3))))
    println(add(List[Option[Int]](Some(1), Some(1), Some(4), Some(3))))
    println(add(List(Order(12, 1), Order(24, 3))))
  }
}

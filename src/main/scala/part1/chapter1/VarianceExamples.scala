package part1.chapter1

object VarianceExamples {

  sealed trait Shape
  case class Circle(radius: Double) extends Shape

  def main(args: Array[String]): Unit = {
    // Covariance: List[+A]
    val circles: List[Circle] = ???
    val shapes: List[Shape] = circles

    // Contravariance
    trait AnotherJsonWriter[-A] {
      def write(value: A): Json
    }

    val shape: Shape = ???
    val circle: Circle = ???

    val shapeWriter: AnotherJsonWriter[Shape] = ???
    val circleWriter: AnotherJsonWriter[Circle] = ???

    def format[A](value: A, writer: AnotherJsonWriter[A]): Json =
      writer.write(value)

    format(shape, shapeWriter)
    format(circle, circleWriter)
//    format(shape, circleWriter) // Not all shapes are circles
    format(circle, shapeWriter)
  }
}
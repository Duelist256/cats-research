package part1.chapter1

trait JsonWriter[A] {
  def write(value: A): Json
}

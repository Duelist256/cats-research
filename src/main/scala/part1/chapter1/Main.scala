package part1.chapter1

object Main {
  def main(args: Array[String]): Unit = {
    // Interface object usage
    import JsonWriterInstances._
    val personJson = Json.toJson(Person("Dave", "dave@example.com"))
    println(personJson)

    // Interface syntax usage
    import JsonSyntax._
    val personJson2 = Person("Joe", "joe@example.com").toJson
    println(personJson2)

    // implicitly examples
    println(implicitly[JsonWriter[String]])
    println(implicitly[JsonWriter[Person]])

    // Recursive implicit resolution
    val option: Option[String] = Option("A string")
    val optionJson = Json.toJson(option)
    println(optionJson)
  }
}

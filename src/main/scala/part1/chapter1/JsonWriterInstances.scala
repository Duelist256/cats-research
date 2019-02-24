package part1.chapter1

object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] = (value: String) => JsString(value)
  implicit val personWriter: JsonWriter[Person] = (value: Person) =>
    JsObject(Map(
      "name" -> JsString(value.name),
      "email" -> JsString(value.email)
    ))

  implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] = new JsonWriter[Option[A]] {
    def write(value: Option[A]): Json = value match {
      case Some(v) => writer.write(v)
      case None => JsNull
    }
  }
}

package karazin.scala.users.group.week6.homework

import scala.language.postfixOps

object givens:
  
  trait JsonStringEncoder[T]:
    def encode(elem: T): String
  
    extension(t: T)
      def toJsonString(): String =
        encode(t)

  given IntEncoder: JsonStringEncoder[Int] with
    def encode(elem: Int): String =
      elem.toString

  given BoolEncoder: JsonStringEncoder[Boolean] with
    def encode(elem: Boolean): String =
      if elem then
        "true"
      else
        "false"

  given StringEncoder: JsonStringEncoder[String] with
    def encode(elem: String): String =
      "\"" + elem + "\""

  given ListEncoder[T](using encoder: => JsonStringEncoder[T]): JsonStringEncoder[List[T]] with
    def encode(list: List[T]): String =
      list.foldLeft("[") { (acc, elem) =>
        if acc == "[" then
          acc + " " + encoder.encode(elem)
        else
          acc + ", " + encoder.encode(elem)
      } + " ]"

  given MapEncoder[V](using encoderValue: => JsonStringEncoder[V])
                             (using encoderKey: => JsonStringEncoder[String]): JsonStringEncoder[Map[String, V]] with
    def encode(map: Map[String, V]): String =
      map.foldLeft("{") {
        case (acc, (key, value)) =>
          if acc == "{" then
            acc + " " + encoderKey.encode(key) + ": " + encoderValue.encode(value)
          else
            acc + ", " + encoderKey.encode(key) + ": " + encoderValue.encode(value)
      } + " }"
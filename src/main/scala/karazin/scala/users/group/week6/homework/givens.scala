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
      "[ " + list.foldLeft(List[String]()) { (acc, elem) =>
        acc :+ elem.toJsonString()
      }.mkString(", ") + " ]"

  given MapEncoder[V](using encoderValue: => JsonStringEncoder[V])
                     (using encoderKey: => JsonStringEncoder[String]): JsonStringEncoder[Map[String, V]] with
    def encode(map: Map[String, V]): String =
      "{ " + map.foldLeft(List[String]()) {
        case (acc, (key, value)) =>
            acc :+ (key.toJsonString() + ": " + value.toJsonString())
      }.mkString(", ") + " }"

  object JsonStringEncoder:
    def apply[V](using encoder: => JsonStringEncoder[V]): JsonStringEncoder[V] =
      return encoder
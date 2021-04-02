package karazin.scala.users.group.week5.homework

object givens:
  
  /* 
    The trait is used for converting instances to a json string representation
    Provide a type parameter(s) for the trait and the method 
    and argument(s) and a result type
  */
  
  trait JsonStringEncoder[T]:
    def encode(elem: T): String
  
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
    def encode(elem: List[T]): String =
      elem.foldLeft("[") {(acc, elem) =>
        if acc == "[" then
          acc + " " + encoder.encode(elem)
        else
          acc + ", " + encoder.encode(elem)
      } + " ]"

  /* 
    Make sure that integers, booleans, strings and lists 
    are convertable to a json string representation 
  */ 
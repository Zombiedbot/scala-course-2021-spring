package karazin.scala.users.group.week7.homework

import scala.language.postfixOps

object semigroups:
  
  /*
   Implement Semigroup
  */
  trait Semigroup[T]:
    extension (first: T) def combine(second: T): T

  given IntGroup: Semigroup[Int] with
    extension (first: Int) def combine(second: Int): Int = first + second

  given BooleanGroup: Semigroup[Boolean] with
    extension (first: Boolean) def combine(second: Boolean): Boolean = first | second

  given StringGroup: Semigroup[String] with
    extension (first: String) def combine(second: String): String = first concat second

  given ListGroup[T]: Semigroup[List[T]] with
    extension (first: List[T]) def combine(second: List[T]): List[T] = first ::: second
  
  given MapGroup[T](using group: => Semigroup[T]): Semigroup[Map[String, T]] with
    extension (first: Map[String, T]) def combine(second: Map[String, T]): Map[String, T] =
      (first ++ second) map { (key, value) =>
        first.get(key) match 
          case Some(firstValue)        => second.get(key) match
            case Some(secondValue)     => (key, firstValue combine secondValue)
            case _                     => (key, value)
          case _                       => (key, value)
      }
   
end semigroups

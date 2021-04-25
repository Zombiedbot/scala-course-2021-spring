package karazin.scala.users.group.week7.homework

object semigroups:
  
  /*
   Implement Semigroup
  */
  trait Semigroup[T]:
    extension (first: T) def combine(second: T): T
  
  given MapGroup[T]: Semigroup[Map[String, T]] with
    extension (first: Map[String, T]) def combine(second: Map[String, T]): Map[String, T] = first ++ second
   
end semigroups

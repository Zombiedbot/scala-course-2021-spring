package karazin.scala.users.group.week7.homework

object functors:

  trait Functor[F[String, _]]:
    def map[A, B](x: F[String, A])(f: A => B): F[String, B]
    
  given MapFunctor[T]: Functor[Map] with
    def map[A, B](x: Map[String, A])(f: A => B): Map[String, B] = x.view.mapValues(f).toMap

  /*
   Implement Functor[Map[String, T]] for any type T
  */

end functors
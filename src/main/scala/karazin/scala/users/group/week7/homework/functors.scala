package karazin.scala.users.group.week7.homework

object functors:
  
  type MapStr[T] = Map[String, T]

  trait Functor[F[_]]:
    def map[A, B](x: F[A])(f: A => B): F[B]
    
  given MapFunctor[T]: Functor[MapStr] with
    def map[A, B](x: MapStr[A])(f: A => B): MapStr[B] = x.view.mapValues(f).toMap

  /*
   Implement Functor[Map[String, T]] for any type T
  */

end functors
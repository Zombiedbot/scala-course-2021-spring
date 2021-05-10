package karazin.scala.users.group.week8.homework


/*
  Provide implementation for Monad trait and provide
  a monad instance for ErrorOr trait from HW2¾

  Hint:
    * treat Monad as a typeclass
    * provide 3 instances:
      * Monad[List]
      * Moand[ErrorOr] for a simple value
      * Monad[ErrorOr] for a F[_], to be able to use List
 */

import  karazin.scala.users.group.week2.and.three.quarters.homework.adt._
import scala.util.{Try,Success,Failure}

object monads:

  trait Monad[F[_]]:
    def pure[A](x: A): F[A]
    
    extension [A, B](x: F[A])
      def flatMap(f: A => F[B]): F[B]

  type EV = [X] =>> ErrorOr[X]
  type EL = [X] =>> ErrorOr[List[X]]
  
  given listMonad: Monad[List] with
    def pure[A](x: A): List[A] =
      List(x)
      
    extension [A, B](xs: List[A])
      def flatMap(f: A => List[B]): List[B] =
        xs.flatMap(f)
  
  given errorOrMonad: Monad[EV] with
    def pure[T](x: T): EV[T] =
      ErrorOr(x)
    
    extension [A, B](eo: EV[A])
      def flatMap(f: A => EV[B]): EV[B] =
        eo.flatMap(f)

  given errorOrListMonad: Monad[EL] with
    def pure[T](x: T): EL[T] =
      ErrorOr(List(x))

    extension [A, B](el: EL[A])
      def flatMap(f: A => EL[B]): EL[B] =
        
        def newFlatMap[A, B](f: A => EL[B])(elem: A): List[B] =
          Try(f(elem)).toErrorOr.flatten match
            case ErrorOr.Err(t)    ⇒ throw t
            case ErrorOr.Or(v)     ⇒ v
        
        el match
          case ErrorOr.Err(t)      ⇒ ErrorOr.Err(t)
          case ErrorOr.Or(l)       ⇒ Try(l.flatMap(newFlatMap(f))).toErrorOr

end monads

package karazin.scala.users.group.week2.and.three.quarters.homework

import scala.util.control.NonFatal
import scala.util.{Try,Success,Failure}
import scala.language.postfixOps

/* 
  Custom implementation of Option (Maybe monad in Haskell)
  Implemented via Scala 3 way for Algebraic Data Types (ADT)
  
  Resources:
  * https://en.wikipedia.org/wiki/Algebraic_data_type
  * https://docs.scala-lang.org/scala3/book/types-adts-gadts.html
*/

object adt:

  extension [T](t: Try[T])
    def toErrorOr: ErrorOr[T] =
      t match
        case Success(value)     ⇒ ErrorOr(value)
        case Failure(exception) ⇒ ErrorOr.Err(exception) 

  enum ErrorOr[+V]:

    // Added to make it compilable. Remove it.
    case Or(x: V) extends ErrorOr[V]
    case Err(t: Throwable) extends ErrorOr[Nothing]

    def flatMap[Q](f: V ⇒ ErrorOr[Q]): ErrorOr[Q] =
      this match
        case ErrorOr.Err(t)      ⇒ ErrorOr.Err(t)
        case ErrorOr.Or(v)       ⇒ Try(f(v)).toErrorOr.flatten


    def map[Q](f: V ⇒ Q): ErrorOr[Q] =
      this match
        case ErrorOr.Err(t)      ⇒ ErrorOr.Err(t)
        case ErrorOr.Or(v)       ⇒ ErrorOr fromTry Try(f(v))

    /* 
      The method is used for filtering
      Provide a type parameter, an argument and a result type
      
      Make sure that in case of failing the method with exception
      no exception is thrown but the case for an error is returned
     */

    def withFilter(p: V => Boolean): ErrorOr[V] = ???

    /* 
      The method is used for getting rid of internal box
      Provide a type parameter, an argument and a result type
    */
    def flatten[U](implicit ev: V <:< ErrorOr[U]): ErrorOr[U] =
      this match
        case ErrorOr.Or(v)  ⇒ ev(v)
        case ErrorOr.Err(t) ⇒ ErrorOr.Err(t)

    /* 
      The method is used for applying side effects without returning any result
      Provide a type parameter, an argument and a result type
    */
    def foreach[U](f: V => U): Unit =
      this match
        case ErrorOr.Err(t)     ⇒ ()
        case ErrorOr.Or(v)      ⇒ f(v)

  // Companion object to define constructor
  object ErrorOr:
    def apply[V](v: V)(implicit ev: V <:< Throwable = null) : ErrorOr[V] =
      Option(ev).fold[ErrorOr[V]](ErrorOr.Or(v))(_ => ErrorOr.Err(v))

    /*
      The method is used for creating `ErroOr` instance from `Try`
      Provide a type parameter, an argument and a result type
    */
    def fromTry[V](res: Try[V]) : ErrorOr[V] = 
      res match
        case Success(value)     ⇒ ErrorOr.Or(value)
        case Failure(exception) ⇒ ErrorOr.Err(exception)
      
  
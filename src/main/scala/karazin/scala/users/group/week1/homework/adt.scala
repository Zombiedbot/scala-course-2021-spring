package karazin.scala.users.group.week1.homework
import scala.util.control.NonFatal
/* 
  Custom implementation of Option (Maybe monad in Haskell)
  Implemented via Scala 3 way for Algebraic Data Types (ADT)
  
  Resources:
  * https://en.wikipedia.org/wiki/Algebraic_data_type
  * https://docs.scala-lang.org/scala3/book/types-adts-gadts.html
*/

object adt:
  
  enum ErrorOr[+V]:
    
    case Or(x: V) extends ErrorOr[V]
    case Err(t: Throwable) extends ErrorOr[Nothing]
    
    
    def flatMap[Q](f: V ⇒ ErrorOr[Q]): ErrorOr[Q] =
      this match
        case ErrorOr.Err(t)      ⇒ ErrorOr.Err(t)
        case ErrorOr.Or(v)       ⇒ 
          try
            f(v)
          catch 
            case NonFatal(t)    ⇒ ErrorOr.Err(t)
    
    
    def map[Q](f: V ⇒ Q): ErrorOr[Q] =
      this match
        case ErrorOr.Err(t)      ⇒ ErrorOr.Err(t)
        case ErrorOr.Or(v)       ⇒
          try
            ErrorOr.Or(f(v))
          catch
            case NonFatal(t)    ⇒ ErrorOr.Err(t)
      
  
  object ErrorOr:
    def apply[V](v: V): ErrorOr[V] =
      if v.isInstanceOf[Throwable] then ErrorOr.Err(v.asInstanceOf[Throwable]) else ErrorOr.Or(v)
      
  
package karazin.scala.users.group.week10.homework

import scala.util.{Try,Success,Failure}
import cats.Applicative
import cats.implicits._

object program:

  /*
    Provide a meaningful example of Applicative usage.
    Where would you try to use Applicative?
   */

  object applicatives:
  
    val f: (Int, Double) => String = (i, d) => i.toString() + ' ' + d.toString()
    
    val int: Try[Int] = Success(42)
    
    val double: Try[Double] = Success(2.28)
    
    val func: Try[Double => String] =
      int.map(i => (d: Double) => f(i, d))
    
    val result: Try[String] =
      Applicative[Try].ap[Double, String](func)(double)
      
  end applicatives

end program

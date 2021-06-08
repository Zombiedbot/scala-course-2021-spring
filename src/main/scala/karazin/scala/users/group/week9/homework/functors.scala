package karazin.scala.users.group.week9.homework

import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import scala.util.{Failure, Success, Try}
import cats.Functor
import cats.implicits.catsStdInstancesForFuture
import cats.instances.all.catsStdInstancesForFuture
import cats.instances.future.catsStdInstancesForFuture

import java.util.concurrent.Executors

object functors extends App:

  def adderFunc(listOption: Future[List[Try[Option[Int]]]], adder: Int)(using Functor[Future]): Future[List[Try[Option[Int]]]] =
    val sigleThreadPoolContext: ExecutionContextExecutorService =
      ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor)
    
    Functor[Future].compose[List].compose[Try].compose[Option].map(listOption) {int => int + adder}

  def multiplierFunc(listOption: Future[List[Try[Option[Int]]]], multiplier: Int)(using Functor[Future]): Future[List[Try[Option[Int]]]] =
    val sigleThreadPoolContext: ExecutionContextExecutorService =
      ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor)

    Functor[Future].compose[List].compose[Try].compose[Option].map(listOption) {int => int * multiplier}
  
  
end functors
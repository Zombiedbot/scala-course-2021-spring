package karazin.scala.users.group.week8.homework

import karazin.scala.users.group.week2.and.three.quarters.homework.adt._
import karazin.scala.users.group.week8.homework.monads.{given, _}
import karazin.scala.users.group.week8.homework.monads._
import karazin.scala.users.group.week8.homework.model.{Comment, Like, Post, Share, UserProfile}

import java.util.UUID

object services:

  /*
    Provide dummy initialization for `getUserProfile`, `getPosts`,
    `getComments`, `getLikes`, `getShares` via Monad[...].pure[...](...)
   */

  def getUserProfile()(using monad: => Monad[EV]): ErrorOr[Int] = monad.pure[Int](42)
  def getPosts(userId: UUID)(using monad: => Monad[EL]): ErrorOr[List[Post]] = monad.pure[Post](Post(UUID.randomUUID(), UUID.randomUUID()))
  def getComments(postId: UUID)(using monad: => Monad[EL]): ErrorOr[List[Comment]] = monad.pure[Comment](Comment(UUID.randomUUID(), UUID.randomUUID()))
  def getLikes(postId: UUID)(using monad: => Monad[EL]): ErrorOr[List[Like]] = monad.pure[Like](Like(UUID.randomUUID(), UUID.randomUUID()))
  def getShares(postId: UUID)(using monad: => Monad[EL]): ErrorOr[List[Share]] = monad.pure[Share](Share(UUID.randomUUID(), UUID.randomUUID()))

end services
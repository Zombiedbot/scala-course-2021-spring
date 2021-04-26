package karazin.scala.users.group.week8.homework

import karazin.scala.users.group.week2.and.three.quarters.homework.adt._
import karazin.scala.users.group.week8.homework.monads.{given, _}
import karazin.scala.users.group.week8.homework.model.{Comment, Like, Post, Share, UserProfile}

import java.util.UUID

object services:

  /*
    Provide dummy initialization for `getUserProfile`, `getPosts`,
    `getComments`, `getLikes`, `getShares` via Monad[...].pure[...](...)
   */

  def getUserProfile(): ErrorOr[Int] = ???
  def getPosts(userId: UUID): ErrorOr[List[Post]] = ???
  def getComments(postId: UUID): ErrorOr[List[Comment]] = ???
  def getLikes(postId: UUID): ErrorOr[List[Like]] = ???
  def getShares(postId: UUID): ErrorOr[List[Share]] = ???

end services
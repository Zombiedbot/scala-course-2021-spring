package karazin.scala.users.group.week3.homework

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import karazin.scala.users.group.week3.homework.services._
import karazin.scala.users.group.week3.homework.model._

import java.util.UUID

class ServicesSuite extends munit.FunSuite {

  test("getUserProfile result") {
    Future {
      val getUserProfileService  = getUserProfile()
      for
        profile     <- getUserProfileService
      yield assert(profile.isInstanceOf[UserProfile])
    }
  }

  test("getPosts result") {
    Future {
      val getPostsService = getPosts(UUID.randomUUID())
      for
        posts    <- getPostsService
      yield assert(
        posts.foldLeft(true) {(acc, elem) => {
          elem.isInstanceOf[Post] && acc
        }}
      )
    }
  }

  test("getComments result") {
    Future {
      val getCommentsService = getComments(UUID.randomUUID())
      for
        comments    <- getCommentsService
      yield assert(
        comments.foldLeft(true) {(acc, elem) => {
          elem.isInstanceOf[Comment] && acc
        }}
      )
    }
  }

  test("getLikes result") {
    Future {
      val getLikesService = getLikes(UUID.randomUUID())
      for
        likes       <- getLikesService
      yield assert(
        likes.foldLeft(true) {(acc, elem) => {
          elem.isInstanceOf[Like] && acc
        }}
      )
    }
  }

  test("getShares result") {
    Future {
      val getSharesService = getShares(UUID.randomUUID())
      for
        shares       <- getSharesService
      yield assert(
        shares.foldLeft(true) {(acc, elem) => {
          elem.isInstanceOf[Share] && acc
        }}
      )
    }
  }
  
}

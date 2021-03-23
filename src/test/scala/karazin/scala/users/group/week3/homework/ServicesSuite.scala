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
      yield profile match
        case UserProfile(userId)  => assert(true)
        case null                 => fail("Wrong result")
    }
  }

  test("getPosts result") {
    Future {
      val userId = UUID.randomUUID()
      val getPostsService = getPosts(userId)
      for
        posts    <- getPostsService
      yield assert(
        posts.foldLeft(true) {(acc, elem) => {
          elem match
            case Post(`userId`, _)     => acc
            case _                     => false
        }}
      )
    }
  }

  test("getComments result") {
    Future {
      val postId = UUID.randomUUID()
      val getCommentsService = getComments(postId)
      for
        comments    <- getCommentsService
      yield assert(
        comments.foldLeft(true) {(acc, elem) => {
          elem match
            case Comment(_, `postId`)  => acc
            case _                     => false
        }}
      )
    }
  }

  test("getLikes result") {
    Future {
      val postId = UUID.randomUUID()
      val getLikesService = getLikes(postId)
      for
        likes       <- getLikesService
      yield assert(
        likes.foldLeft(true) {(acc, elem) => {
          elem match
            case Like(_, `postId`)     => acc;
            case _                     => false;
        }}
      )
    }
  }

  test("getShares result") {
    Future {
      val postId = UUID.randomUUID()
      val getSharesService = getShares(postId)
      for
        shares       <- getSharesService
      yield assert(
        shares.foldLeft(true) {(acc, elem) => {
          elem match
            case Share(_, `postId`)    => acc;
            case _                     => false;
        }}
      )
    }
  }
  
}

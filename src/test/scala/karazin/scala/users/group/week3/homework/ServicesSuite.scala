package karazin.scala.users.group.week3.homework

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import karazin.scala.users.group.week3.homework.services._
import karazin.scala.users.group.week3.homework.model._

import java.util.UUID

class ServicesSuite extends munit.FunSuite {

  test("getUserProfile result") {
      getUserProfile() map { profile =>
        profile match
          case UserProfile(_)     => assert(true)
          case null               => fail("Wrong result")
      }
  }

  test("getPosts result") {
    val userId = UUID.randomUUID()
    getPosts(userId) map { posts =>
      assert(
        posts.foldLeft(true) {(acc, elem) => {
          elem match
            case Post(`userId`, _)     => acc
            case _                     => false
        }}
      )
    }
  }

  test("getComments result") {
    val postId = UUID.randomUUID()
    getComments(postId) map {comments =>
      assert(
        comments.foldLeft(true) {(acc, elem) => {
          elem match
            case Comment(_, `postId`)  => acc
            case _                     => false
        }}
      )
    }
  }

  test("getLikes result") {
    val postId = UUID.randomUUID()
    getLikes(postId) map { likes =>
      assert(
        likes.foldLeft(true) {(acc, elem) => {
          elem match
            case Like(_, `postId`)     => acc
            case _                     => false
        }}
      )
    }
  }

  test("getShares result") {
    val postId = UUID.randomUUID()
    getShares(postId) map { shares =>
      assert(
        shares.foldLeft(true) {(acc, elem) => {
          elem match
            case Share(_, `postId`)    => acc
            case _                     => false
        }}
      )
    }
  }
  
}

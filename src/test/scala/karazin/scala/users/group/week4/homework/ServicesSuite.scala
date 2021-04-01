package karazin.scala.users.group.week4.homework

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import karazin.scala.users.group.week4.homework.services._
import karazin.scala.users.group.week4.homework.model._

import java.util.UUID
import java.util.concurrent.Executors

/*
  Write test for all service in karazin.scala.users.group.week4.homework.services
  Make sure you control custom execution contexts in tests using `before` and `after` logic

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/fixtures.html#ad-hoc-test-local-fixtures
 */

class ServicesSuite extends munit.FunSuite:

  val sigleThreadPoolContext = 
    new Fixture[ExecutionContext]("files") {
      var threadpool: ExecutionContextExecutorService = null
      def apply() = threadpool
      override def beforeEach(context: BeforeEach): Unit = {
        threadpool = ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor)
      }
      override def afterEach(context: AfterEach): Unit = {
        // Always gets called, even if test failed.
        threadpool.shutdown()
      }
    }
  override def munitFixtures = List(sigleThreadPoolContext)

  test("getUserProfile result") {
    Future {
      val getUserProfileService  = getUserProfile(sigleThreadPoolContext())
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
      val getPostsService = getPosts(userId)(sigleThreadPoolContext())
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
      val getCommentsService = getComments(postId)(sigleThreadPoolContext())
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
      val getLikesService = getLikes(postId)(sigleThreadPoolContext())
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
      val getSharesService = getShares(postId)(sigleThreadPoolContext())
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
  
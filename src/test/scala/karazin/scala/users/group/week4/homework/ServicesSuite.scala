package karazin.scala.users.group.week4.homework

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import karazin.scala.users.group.week4.homework.services._
import karazin.scala.users.group.week4.homework.model._

import java.util.UUID
import java.util.concurrent.Executors
import scala.util.{Try,Success,Failure}

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

  private def checkList[V](list: List[V])(f: V => Unit) =
    list foreach f

  test("getUserProfile result") {
    val getUserProfileService  = getUserProfile(using sigleThreadPoolContext())
    getUserProfileService map {
      case UserProfile(_)                      => assert(true)
    } recover {
      case error => fail("Exception Thrown")
    }
  }

  test("getPosts result") {
      val userId = UUID.randomUUID()
      val getPostsService = getPosts(userId)(using sigleThreadPoolContext())
      getPostsService map {
        case listPost: List[_]          => checkList[Post](listPost) { p => 
          assertEquals(userId, p.userId)
        }
      } recover {
        case error => fail("Exception Thrown")
      }
  }

  test("getComments result") {
    val postId = UUID.randomUUID()
    val getCommentsService = getComments(postId)(using sigleThreadPoolContext())
    getCommentsService map {
      case listComments: List[_]          => checkList[Comment](listComments) { c =>
        assertEquals(postId, c.postId)
      }
    } recover {
      case error => fail("Exception Thrown")
    }
  }

  test("getLikes result") {
    val postId = UUID.randomUUID()
    val getLikesService = getLikes(postId)(using sigleThreadPoolContext())
    getLikesService map {
      case listLikes: List[_]          => checkList[Like](listLikes) { l =>
        assertEquals(postId, l.postId)
      }
    } recover {
      case error => fail("Exception Thrown")
    }
  }

  test("getShares result") {
    val postId = UUID.randomUUID()
    val getSharesService = getShares(postId)(using sigleThreadPoolContext())
    getSharesService map {
      case listShares: List[_]          => checkList[Share](listShares) { s =>
        assertEquals(postId, s.postId)
      }
    } recover {
      case error => fail("Exception Thrown")
    }
  }
  
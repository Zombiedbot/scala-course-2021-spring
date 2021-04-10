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

  test("getUserProfile result") {
    val getUserProfileService  = getUserProfile(sigleThreadPoolContext())
    getUserProfileService onComplete {
      case Success(UserProfile(_))  => assert (true)
      case _                        => fail("Exception Thrown or wrong type")
    }
  }

  test("getPosts result") {
      val userId = UUID.randomUUID()
      val getPostsService = getPosts(userId)(sigleThreadPoolContext())
      getPostsService onComplete {
        case Success(listPost: List[Post]) => assert (
            listPost.foldLeft (true) {
              (acc, elem) => {
                elem match
                  case Post (`userId`, _) => acc
                  case _                  => false
              }
            })
        case _                 => fail("Exception Thrown or wrong type")
      }
  }

  test("getComments result") {
    val postId = UUID.randomUUID()
    val getCommentsService = getComments(postId)(sigleThreadPoolContext())
    getCommentsService onComplete {
      case Success(listComments: List[Comment]) => assert (
        listComments.foldLeft (true) {
          (acc, elem) => {
            elem match
              case Comment(_, `postId`) => acc
              case _                    => false
          }
        })
      case _                 => fail("Exception Thrown or wrong type")
    }
  }

  test("getLikes result") {
    val postId = UUID.randomUUID()
    val getLikesService = getLikes(postId)(sigleThreadPoolContext())
    getLikesService onComplete {
      case Success(listLikes: List[Like]) => assert (
        listLikes.foldLeft (true) {
          (acc, elem) => {
            elem match
              case Like(_, `postId`)    => acc
              case _                    => false
          }
        })
      case _                 => fail("Exception Thrown or wrong type")
    }
  }

  test("getShares result") {
    val postId = UUID.randomUUID()
    val getSharesService = getShares(postId)(sigleThreadPoolContext())
    getSharesService onComplete {
      case Success(listShares: List[Share]) => assert (
        listShares.foldLeft (true) {
          (acc, elem) => {
            elem match
              case Share(_, `postId`)    => acc
              case _                    => false
          }
        })
      case _                 => fail("Exception Thrown or wrong type")
    }
  }
  
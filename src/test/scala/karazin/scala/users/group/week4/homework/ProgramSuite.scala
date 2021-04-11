package karazin.scala.users.group.week4.homework

import karazin.scala.users.group.week4.homework.model._
import karazin.scala.users.group.week4.homework.program._
import karazin.scala.users.group.week4.homework.services._

import java.util.UUID
import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import scala.util.{Try,Success,Failure}

/*
  Write test for all programs in karazin.scala.users.group.week4.homework.program
  Make sure you control custom execution contexts in tests using `before` and `after` logic

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/fixtures.html#ad-hoc-test-local-fixtures
 */

class ProgramSuite extends munit.FunSuite:

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

  test("getPostView result") {
    val post = Post(UUID.randomUUID(), UUID.randomUUID())
    val getPostViewService  = getPostView(post)(using sigleThreadPoolContext())
    getPostViewService map { res => res match
      case PostView(`post`, _, _, _)          => assert(true)
      case _                                  => fail("Wrong result")
    } recover {
      case error => fail("Exception Thrown")
    }
  }

  test("getPostViews result") {
    val getPostsViewService  = getPostsViews(using sigleThreadPoolContext())
    getPostsViews map { res => assert (true) } recover {
      case error => fail("Exception Thrown")
    }
  }
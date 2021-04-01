package karazin.scala.users.group.week4.homework

import karazin.scala.users.group.week4.homework.model._
import karazin.scala.users.group.week4.homework.program._
import karazin.scala.users.group.week4.homework.services._

import java.util.UUID
import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}

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
    Future {
      val post = Post(UUID.randomUUID(), UUID.randomUUID())
      val getPostViewService  = getPostView(post)(sigleThreadPoolContext())
      for
        postView     <- getPostViewService
      yield postView match
        case PostView(`post`, _, _, _)  => assert(true)
        case _                          => fail("Wrong result")
    }
  }

  test("getPostViews result") {
    Future {
      val getPostsViewService  = getPostsViews(sigleThreadPoolContext())
      for
        postsView     <- getPostsViews
      yield assert(
        postsView.foldLeft(true) {(acc, elem) => {
          elem match
            case PostView(_, _, _, _)     => acc
            case null                     => false
        }}
      )
    }
  }
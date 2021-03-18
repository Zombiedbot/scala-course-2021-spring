package karazin.scala.users.group.week3.homework

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import karazin.scala.users.group.week3.homework.model._
import karazin.scala.users.group.week3.homework.program._

import java.util.UUID

class ProgramSuite extends munit.FunSuite {

  test("getPostView result") {
    Future {
      val getPostViewService      = getPostView(Post(userId = UUID.randomUUID(), postId = UUID.randomUUID()))
      for
        postView     <- getPostViewService 
      yield assert(postView.isInstanceOf[PostView])
    }
  }
}
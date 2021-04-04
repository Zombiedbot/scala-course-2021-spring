package karazin.scala.users.group.week3.homework

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import karazin.scala.users.group.week3.homework.model._
import karazin.scala.users.group.week3.homework.program._

import java.util.UUID

class ProgramSuite extends munit.FunSuite {

  test("getPostView result") {
    val userId = UUID.randomUUID()
    val postId = UUID.randomUUID()
    getPostView(Post(userId, postId)) map { postView =>
      postView match
        case PostView(Post(`userId`, `postId`), _, _, _)  => assert(true)
        case _                                            => fail("Wrong result")
    }
  }
}
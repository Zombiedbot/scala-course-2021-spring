package karazin.scala.users.group.week10.topic

import karazin.scala.users.group.week10.topic.polymorphicfunctions.`I₂³`
import karazin.scala.users.group.week10.topic.polymorphicfunctions.`(f ० g)(x)`
import karazin.scala.users.group.week10.topic.applicatives.getPostViewWithExtras
import karazin.scala.users.group.week10.topic.model._
import karazin.scala.users.group.week10.topic.services._

import java.util.UUID
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object program:

  object `polymorphic functions`:

    val result11: Double = `I₂³`(42)(1.0)(true)
    val result12: String = `I₂³`(42)("Hello world")(true)

    val result21: String = `(f ० g)(x)`((v: Int) ⇒ "Result: v")((v: Boolean) ⇒ if v then 1 else 0)(true)
    val result22: Int = `(f ० g)(x)`((v: Boolean) ⇒ if v then 42 else Int.MinValue)((v: String) ⇒ v == "Hello world")("Hello world")

  end `polymorphic functions`

  object applicatives:

    val postId = UUID.randomUUID()

    val postViewWithComments: Future[PostView[Comment]] =
      getPostViewWithExtras(postId)(getComments(postId))

    val postViewWithShares: Future[PostView[Share]] =
      getPostViewWithExtras(postId)(getShares(postId))

    val postViewWithLikes: Future[PostView[Like]] =
      getPostViewWithExtras(postId)(getLikes(postId))

  end applicatives


end program

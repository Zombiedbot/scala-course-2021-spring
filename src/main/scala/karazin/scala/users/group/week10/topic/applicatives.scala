package karazin.scala.users.group.week10.topic


import java.util.UUID
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import cats.Applicative
import cats.implicits._

import karazin.scala.users.group.week10.topic.model._
import karazin.scala.users.group.week10.topic.services.getPost

object applicatives:

  val postViewComposer: [Extra] ⇒ (Post, List[Extra]) ⇒ PostView[Extra] =
    [Extra] ⇒ (post: Post, extras: List[Extra]) ⇒ PostView(post, extras)

  val getPostViewWithExtras: [Extra] ⇒ UUID ⇒ Future[List[Extra]] ⇒ Future[PostView[Extra]] =
    [Extra] ⇒ (postId: UUID) ⇒
      Applicative[Future].ap[List[Extra], PostView[Extra]] {
        getPost(postId) map { post ⇒
          (extras: List[Extra]) ⇒ postViewComposer(post, extras)
        }
      }

end applicatives
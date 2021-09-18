package karazin.scala.users.group.week10.topic

import java.util.UUID
import scala.concurrent.Future
import karazin.scala.users.group.week10.topic.model._

object services:

   val getPost: UUID ⇒ Future[Post] = ???
   val getComments: UUID ⇒ Future[List[Comment]] = ???
   val getLikes: UUID ⇒ Future[List[Like]] = ???
   val getShares: UUID ⇒ Future[List[Share]] = ???

end services
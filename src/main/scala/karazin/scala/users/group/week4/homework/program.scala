package karazin.scala.users.group.week4.homework

import java.util.UUID
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import scala.util.Success
import scala.util.Failure
import karazin.scala.users.group.week4.homework.model._
import karazin.scala.users.group.week4.homework.services._

import java.util.concurrent.Executors
import scala.language.postfixOps

object program:
  // Make sure that the result type is exactly `Future[List[PostView]]`
  // not `Future[List[Future[PostView]]]`

  given ExecutionContext = ExecutionContext.global
  
  def getPostsViews(using ec: ExecutionContext): Future[List[PostView]] =
    
    val fixedThreadPoolContext: ExecutionContextExecutorService = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(3))
    val getUserProfileService  = getUserProfile(using fixedThreadPoolContext)
    
    for
      profile        ← getUserProfileService
      posts          ← getPosts(profile.userId)(using fixedThreadPoolContext)
      postsView      = posts map { post ⇒ getPostView(post) }
      postViews      ← Future.foldLeft(postsView)(List[PostView]()) { (acc, elem) => elem :: acc }
    yield postViews
  
  def getPostView(post: Post)(using ec: ExecutionContext): Future[PostView] =

    val fixedThreadPoolContext: ExecutionContextExecutorService = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(3))
    
    val getCommentsService  = getComments(post.postId)(using fixedThreadPoolContext)
    val getLikesService     = getLikes(post.postId)(using fixedThreadPoolContext)
    val getSharesService    = getShares(post.postId)(using fixedThreadPoolContext)
    
    for
      comments  ← getCommentsService
      likes     ← getLikesService
      shares    ← getSharesService
    yield  {
      fixedThreadPoolContext.shutdown()
      PostView(post, comments, likes, shares)
    }


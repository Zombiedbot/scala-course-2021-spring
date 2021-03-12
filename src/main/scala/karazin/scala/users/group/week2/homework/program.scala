package karazin.scala.users.group.week2.homework

// Do not forget to import custom implementation
import adt._
import model._
import services._
import scala.language.postfixOps

object program:
  /*
   Print all view for all user's posts if they exists
  */
  def printPostsViews(): ErrorOr[List[PostView]] = {
    for
      postViews     ← getPostsViews()
    yield {
      postViews.foreach(print) 
      postViews
    }
  }
  /*
   Getting view for all user's posts if they exists
  */
  // I chose to skip errors
  def getPostsViews(): ErrorOr[List[PostView]] = {
    for
      profile        ← getUserProfile()
      posts          ← getPosts(profile.userId)
      if posts.nonEmpty
      postsView      ← ErrorOr( posts map { post ⇒ getPostView(post) } )
    yield postsView.foldLeft(List[PostView]()) { (acc, elem) =>
      elem match 
        case ErrorOr.Or(v)    => acc ::: List[PostView](v)
        case _                => acc
    }
  }

  /* 
    Getting view for a particular user's post
    Provide an argument and a result type
  */
  def getPostView(post: Post): ErrorOr[PostView] = {
    for
      comments  ← getComments(post.postId)
      likes     ← getLikes(post.postId)
      shares    ← getShares(post.postId) 
    yield PostView(post, comments, likes, shares)
  }

  /*
   Provide desugared version of the previous two methods
  */
  def getPostsViewDesugared(post: Post): ErrorOr[PostView] = {
    getComments(post.postId) flatMap { comments =>
      getLikes(post.postId) flatMap { likes =>
        getShares(post.postId) flatMap { shares =>
          ErrorOr(PostView(post, comments, likes, shares))
        }
      }
    }
  } 


  
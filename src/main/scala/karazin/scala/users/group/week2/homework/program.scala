package karazin.scala.users.group.week2.homework

// Do not forget to import custom implementation
import adt._
import model._
import services._

object program:
  
  /*
   Getting view for all user's posts if they exists
  */
  // HELP: I don't know how to do it right. It looks like shit :)
  def getPostsViews(): ErrorOr[List[PostView]] = {
    for
      profile        ← getUserProfile()
      posts          ← getPosts(profile.userId)
      if posts.nonEmpty
      postsView      ← ErrorOr( posts map { post ⇒ getPostView(post) } )
      clearPostsView ← ErrorOr( postsView filter { p => p.unpack().isInstanceOf[PostView] })
      clearer        ← ErrorOr( clearPostsView map {p => p.unpack().asInstanceOf[PostView] })
    yield clearer
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


  
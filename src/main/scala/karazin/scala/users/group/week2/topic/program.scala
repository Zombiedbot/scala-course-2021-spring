package karazin.scala.users.group.week2.topic

// Do not forget to import custom implementation
import adt._

import model._
import services._

object program:

  // Getting view for all user's posts
  def getPostsViews(): Option[List[Option[PostView]]] = {
    for
      profile   ← getUserProfile()
      posts     ← getPosts(profile.userId)
      postsView ← Option(posts map { post ⇒ getPostView(post) })
    yield postsView
  }

  // Getting view for a particular user's post
  def getPostView(post: Post): Option[PostView] = {
    for
      comments  ← getComments(post.postId)
      likes     ← getLikes(post.postId)
      shares    ← getShares(post.postId)
    yield PostView(post, comments, likes, shares)
  }

  // Desugared version of the previous two methods
  def getPostsViewDesugared(): Option[List[Option[PostView]]] = ???
package karazin.scala.users.group.week4.homework

object utils:
  def checkList[V](list: List[V])(f: V => Unit) =
    list foreach f
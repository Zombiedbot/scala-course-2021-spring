package karazin.scala.users.group.week6.homework

import karazin.scala.users.group.week6.homework.givens.{given, _}
import org.scalacheck._
import Prop.{forAll, propBoolean}
import scala.language.postfixOps

object basicTypesCheck extends Properties("Basic types"):

  property("intCheck") = forAll { (n: Int) =>
    n.toJsonString() == n.toString()
  }

  property("stringCheck") = forAll { (s: String) =>
    s.toJsonString() == "\"" + s + "\""
  }

  property("boolCheck") = forAll { (b: Boolean) =>
    b.toJsonString() == b.toString()
  }
  
end basicTypesCheck

object listCheck extends Properties("List types"):
  
  def getSampleString[T](list: List[T])(using encoder: => JsonStringEncoder[T]): String =
    list.map { (elem) =>
      elem.toJsonString()
    }.mkString("[ ", ", ", " ]")

  property("listInt") = forAll { (li: List[Int]) =>
    li.toJsonString() == getSampleString(li)
  }

  property("listString") = forAll { (ls: List[String]) =>
    ls.toJsonString() == getSampleString(ls)
  }

  property("listBool") = forAll { (lb: List[Boolean]) =>
    lb.toJsonString() == getSampleString(lb)
  }

end listCheck

object mapCheck extends Properties("Map types"):
  
  def getSampleString[V](map: Map[String, V])(using encoder: => JsonStringEncoder[V]): String =
    map.foldLeft(List[String]()) {
      case (acc, (key, value)) =>
        acc :+ (key.toJsonString() + ": " + value.toJsonString())
    }.mkString("{ ", ", ", " }")

  property("mapInt") = forAll { (mi: Map[String, Int]) =>
    mi.toJsonString() == getSampleString(mi)
  }

  property("mapString") = forAll { (ms: Map[String, String]) =>
    ms.toJsonString() == getSampleString(ms)
  }

  property("mapBool") = forAll { (mb: Map[String, Boolean]) =>
    mb.toJsonString() == getSampleString(mb)
  }
  
end mapCheck
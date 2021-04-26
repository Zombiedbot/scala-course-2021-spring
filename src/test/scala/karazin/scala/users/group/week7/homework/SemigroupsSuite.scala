package karazin.scala.users.group.week7.homework

/*
  Write test for semigroups

  Make sure that the following cases are tested:
    • Map[String, Int] works
    • Map[String, Boolean] works
    • Map[String, String] works
    • Map[String, List[Int]], Map[String, List[Boolean]], Map[String, List[String]] works

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/assertions.html#compileerrors
    • https://scalameta.org/munit/docs/integrations/scalacheck.html

 */

import org.scalacheck._
import Prop.{forAll, propBoolean}
import karazin.scala.users.group.week7.homework.semigroups.{given, _}
import scala.language.postfixOps

object SemigroupsSuite extends Properties("Semigroup Basic types"):
  
  def testCombine[T](first: Map[String, T], second: Map[String, T])(using Semigroup[T]): Map[String, T] = 
    var res = collection.mutable.Map[String, T]()
    res = (res ++ first)
    second foreach { (key, value) =>
      res.get(key) match 
        case Some(anotherValue) => res.update(key, anotherValue combine value)
        case _                  => res.update(key, value)
    }
    res.toMap
    

  property("semigroup Map[String, Int]") =
    forAll { (left: Map[String, Int], right: Map[String, Int]) =>
      (left combine right) == testCombine[Int](left, right)
    }

  property("semigroup Map[String, String]") =
    forAll { (left: Map[String, String], right: Map[String, String]) =>
      (left combine right) == testCombine[String](left, right)
    }
  
  property("semigroup Map[String, Boolean]") =
    forAll { (left: Map[String, Boolean], right: Map[String, Boolean]) =>
      (left combine right) == testCombine[Boolean](left, right)
    }
  
  property("semigroup Map[String, List[Int]]") =
    forAll { (left: Map[String, List[Int]], right: Map[String, List[Int]]) =>
      (left combine right) == testCombine[List[Int]](left, right)
    }
  
  property("semigroup Map[String, List[String]]") =
    forAll { (left: Map[String, List[String]], right: Map[String, List[String]]) =>
      (left combine right) == testCombine[List[String]](left, right)
    }
  
  property("semigroup Map[String, List[Boolean]]") =
    forAll { (left: Map[String, List[Boolean]], right: Map[String, List[Boolean]]) =>
      (left combine right) == testCombine[List[Boolean]](left, right)
    }

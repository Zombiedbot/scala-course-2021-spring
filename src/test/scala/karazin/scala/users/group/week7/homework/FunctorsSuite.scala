package karazin.scala.users.group.week7.homework

/*
  Write test for functors

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
import scala.language.postfixOps
import karazin.scala.users.group.week7.homework.functors.{given, _}
import karazin.scala.users.group.week7.homework.functors.Functor

object FunctorsSuiteBasic extends Properties("Functor Basic types"):

  property("functor Map[String, Int] to int") =
    forAll { (map: Map[String, Int], f: Int => Int) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap    
    }

  property("functor Map[String, Int] to string") =
    forAll { (map: Map[String, Int], f: Int => String) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, Int] to boolean") =
    forAll { (map: Map[String, Int], f: Int => Boolean) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, String] to int") =
    forAll { (map: Map[String, String], f: String => Int) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, String] to string") =
    forAll { (map: Map[String, String], f: String => String) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, String] to boolean") =
    forAll { (map: Map[String, String], f: String => Boolean) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, Boolean] to int") =
    forAll { (map: Map[String, Boolean], f: Boolean => Int) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, Boolean] to string") =
    forAll { (map: Map[String, Boolean], f: Boolean => String) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, Boolean] to boolean") =
    forAll { (map: Map[String, Boolean], f: Boolean => Boolean) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }


object FunctorsSuiteLists extends Properties("Functor Basic types"):

  property("functor Map[String, List[Int]] to List[Int]") =
    forAll { (map: Map[String, List[Int]], f: List[Int] => List[Int]) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, List[Int]] to List[String]") =
    forAll { (map: Map[String, List[Int]], f: List[Int] => List[String]) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, List[Int]] to List[Boolean]") =
    forAll { (map: Map[String, List[Int]], f: List[Int] => List[Boolean]) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, List[String]] to List[Int]") =
    forAll { (map: Map[String, List[String]], f: List[String] => List[Int]) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, List[String]] to List[String]") =
    forAll { (map: Map[String, List[String]], f: List[String] => List[String]) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, List[String]] to List[Boolean]") =
    forAll { (map: Map[String, List[String]], f: List[String] => List[Boolean]) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, List[Boolean]] to List[Int]") =
    forAll { (map: Map[String, List[Boolean]], f: List[Boolean] => List[Int]) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, List[Boolean]] to List[String]") =
    forAll { (map: Map[String, List[Boolean]], f: List[Boolean] => List[String]) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

  property("functor Map[String, List[Boolean]] to List[Boolean]") =
    forAll { (map: Map[String, List[Boolean]], f: List[Boolean] => List[Boolean]) =>
      summon[Functor[MapStr]].map(map)(f) == map.view.mapValues(f).toMap
    }

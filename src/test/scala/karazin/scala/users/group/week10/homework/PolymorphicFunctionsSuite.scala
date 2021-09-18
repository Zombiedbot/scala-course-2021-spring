package karazin.scala.users.group.week10.homework

import karazin.scala.users.group.week10.homework.polymorphicfunctions._
import munit.ScalaCheckSuite
import org.scalacheck._
import org.scalacheck.Prop._


class PolymorphicFunctionsSuite extends ScalaCheckSuite:
  
  val `gen Char => Int`: Gen[Char => Int] = Gen.const((c: Char) => c.toInt)
  val `gen Int => Double`: Gen[Int => Double] = Gen.const((i: Int) => i * 3.14)
  val `gen Double => String`: Gen[Double => String] = Gen.const((d: Double) => d.toString)
  val `gen String => List[Char]`: Gen[String => List[Char]] = Gen.const((s: String) ⇒ s.toList)
  
  type charToInt = Char => Int
  type intToDouble = Int => Double
  type doubleToString = Double => String
  type stringToListChar = String => List[Char]
  
  given Arbitrary[charToInt] = Arbitrary(`gen Char => Int`)
  given Arbitrary[intToDouble] = Arbitrary(`gen Int => Double`)
  given Arbitrary[doubleToString] = Arbitrary(`gen Double => String`)
  given Arbitrary[stringToListChar] = Arbitrary(`gen String => List[Char]`)

  property("`I₍₂,₄₎⁴` test") {

    /*
      Provide more specific types instead of Unknown

      Hint:
        `I₍₂,₄₎⁴`(unknown1)(unknown2)(unknown3)(unknown4) == (unknown2, unknown4)
     */

    forAll { (i: Int, s: String, d: Double, c: Char) =>
      `I₍₂,₄₎⁴`(i)(s)(d)(c) == (s, c)
    }

  }

  property("`(f ० g ० h ० i)(x)` test") {

    /*
      Implement a sound generator for a quidriple of (f, g, h, i) and x

      Hint:
        `(f ० g ० h ० i)(x)`(unknown1)(unknown2)(unknown3)(unknown4)(unknown4) == ???
     */

    forAll { (f: String ⇒ List[Char], g: Double ⇒ String, h: Int ⇒ Double, i: Char ⇒ Int, c: Char) =>
      `(f ० g ० h ० i)(x)`(f)(g)(h)(i)(c) == f(g(h(i(c))))
    }

  }
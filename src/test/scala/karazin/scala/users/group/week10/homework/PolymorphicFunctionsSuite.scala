package karazin.scala.users.group.week10.homework

import karazin.scala.users.group.week10.homework.polymorphicfunctions._
import munit.ScalaCheckSuite
import org.scalacheck._
import org.scalacheck.Prop._


class PolymorphicFunctionsSuite extends ScalaCheckSuite:

  trait Unknown
  val `gen Unknown`: Gen[Unknown] = Gen.const(new Unknown {})
  val `gen Unknown => Unknown`: Gen[Unknown => Unknown] = Gen.const((v: Unknown) ⇒ v)
  given Arbitrary[Unknown] = Arbitrary(`gen Unknown`)
  given Arbitrary[Unknown => Unknown] = Arbitrary(`gen Unknown => Unknown`)

  property("`I₍₂,₄₎⁴` test") {

    /*
      Provide more specific types instead of Unknown

      Hint:
        `I₍₂,₄₎⁴`(unknown1)(unknown2)(unknown3)(unknown4) == (unknown2, unknown4)
     */

    forAll { (unknown1: Unknown, unknown2: Unknown, unknown3: Unknown, unknown4: Unknown) =>
      unknown1 == unknown2
    }

  }

  property("`(f ० g ० h ० i)(x)` test") {

    /*
      Implement a sound generator for a quidriple of (f, g, h, i) and x

      Hint:
        `(f ० g ० h ० i)(x)`(unknown1)(unknown2)(unknown3)(unknown4)(unknown4) == ???
     */

    forAll { (unknown1: Unknown ⇒ Unknown, unknown2: Unknown ⇒ Unknown, unknown3: Unknown ⇒ Unknown, unknown4: Unknown ⇒ Unknown, unknown5: Unknown) =>
      unknown1 == unknown2
    }

  }
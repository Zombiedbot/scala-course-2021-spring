package karazin.scala.users.group.week5.homework

import karazin.scala.users.group.week5.homework.givens.{given, _}

import scala.concurrent.Future

/*
  Write test for all programs in karazin.scala.users.group.week4.homework.givens

  Make sure that the following cases are tested:
    • json string representation for integers works
    • json string representation for booleans works
    • json string representation for strings works
    • json string representation for lists for integers, booleans and strings works
    • json string representation for maps fails on compile time

  Review:
    • https://www.json.org/json-en.html
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/assertions.html#compileerrors
    
  NB: Do not use sync, this homework does not belong async stuff
    
 */
class GivensSuite extends munit.FunSuite:
  
  test("Int JSON") {
    assertEquals(
      JsonStringEncoder[Int]
        .encode(42), "42"
    )
  }

  test("Bool JSON") {
    assertEquals(
      JsonStringEncoder[Boolean]
        .encode(true), "true"
    )
  }

  test("String JSON") {
    assertEquals(
      JsonStringEncoder[String]
        .encode("Some String"), "\"Some String\""
    )
  }

  test("List String JSON") {
    assertEquals(
      JsonStringEncoder[List[String]]
        .encode("one" :: "two" :: "three" :: Nil), "[ \"one\", \"two\", \"three\" ]"
    )
  }

  test("List Booleans JSON") {
    assertEquals(
      JsonStringEncoder[List[Boolean]]
        .encode(true :: false :: true :: Nil), "[ true, false, true ]"
    )
  }

  test("List Integers JSON") {
    assertEquals(
      JsonStringEncoder[List[Int]]
        .encode(234 :: -13 :: 0 :: Nil), "[ 234, -13, 0 ]"
    )
  }

  test("List Empty JSON") {
    assertEquals(
      JsonStringEncoder[List[Int]]
        .encode(Nil), "[  ]"
    )
  }

  test("Map fails") {
    compileErrors("summon[JsonStringEncoder[Map[String, Int]]].encode(Map(\"x\" -> 24, \"y\" -> 25, \"z\" -> 26))")
  }
import sbt._

object Dependencies {
  
  object Version {
    val `scala3-compiler`             = "3.0.0-M3"
    val cats                          = "2.3.1"
    val `cats-effect`                 = "3.0.0-M5"
    val munit                         = "0.7.22"
  }

  lazy val `scala3-compiler`: Seq[ModuleID] = Seq(
    "org.scala-lang" % "scala3-compiler_3.0.0-M3",
  ).map(_ % Version.`scala3-compiler` withSources() withJavadoc())

  lazy val cats: Seq[ModuleID] = Seq(
    "org.typelevel" %% "cats-core",
    "org.typelevel" %% "cats-laws",
    "org.typelevel" %% "cats-free",
    "org.typelevel" %% "cats-testkit",
    "org.typelevel" %% "alleycats-core"
  ).map(_ % Version.cats withSources() withJavadoc())

  lazy val `cats-effect`: Seq[ModuleID] = Seq(
    "org.typelevel" %% "cats-effect"
  ).map(_ % Version.`cats-effect` withSources() withJavadoc())
  
  lazy val munit: Seq[ModuleID] = Seq(
    "org.scalameta" %% "munit"
  ).map(_ % Version.munit % Test withSources() withJavadoc())  

}

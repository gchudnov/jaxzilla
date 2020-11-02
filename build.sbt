import sbt.Keys._
import sbt._

Global / cancelable := true

def testFilter(name: String): Boolean = (name endsWith "Spec")

lazy val testSettings = Seq(
  testOptions in Test ++= Seq(Tests.Filter(testFilter))
)

lazy val allSettings = Settings.shared ++ testSettings

lazy val root = (project in file("."))
  .settings(allSettings: _*)
  .settings(Settings.sonatype)
  .settings(
    name := "jaxzilla",
    libraryDependencies ++= Dependencies.Jaxzilla
  )

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("chk", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")

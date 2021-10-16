import sbt.Keys._
import sbt._

Global / cancelable := true

def testFilter(name: String): Boolean = (name endsWith "Spec")

lazy val testSettings = Seq(
  Test / testOptions ++= Seq(Tests.Filter(testFilter))
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
addCommandAlias("plg", "; reload plugins ; libraryDependencies ; reload return")
// NOTE: to use version check for plugins, add to the meta-project (/project/proect) sbt-updates.sbt with "sbt-updates" plugin as well.
addCommandAlias("upd", ";dependencyUpdates; reload plugins; dependencyUpdates; reload return")

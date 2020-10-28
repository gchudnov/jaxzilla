import sbt._

object Dependencies {

  object versions {
    val kindProjector      = "0.10.3"
    val scalaTest      = "3.2.2"
  }

  // compiler plugins
  private val kindProjector = compilerPlugin("org.typelevel" %% "kind-projector" % versions.kindProjector)

  private val compiler = Seq(
    kindProjector
  )

  private val scalatest     = "org.scalatest"     %% "scalatest"       % versions.scalaTest

  val Jaxzilla: Seq[ModuleID] = {
    val compile = Seq.empty[ModuleID]
    val test = Seq(
      scalatest
    ) map (_ % "test")
    compile ++ test ++ compiler
  }
}

import com.jsuereth.sbtpgp.PgpKeys
import com.jsuereth.sbtpgp.SbtPgp.autoImport.usePgpKeyHex
import sbt.Keys._
import sbt._
import sbtrelease.ReleasePlugin.autoImport._
import sbtrelease.ReleaseStateTransformations._
import xerial.sbt.Sonatype.autoImport.sonatypePublishToBundle

object Settings {
  private val scala213 = "2.13.6"
  private val scalaV   = scala213

  private val sharedScalacOptions = Seq(
    "-deprecation", // Emit warning and location for usages of deprecated APIs.
    "-encoding",
    "utf-8",                         // Specify character encoding used by source files.
    "-explaintypes",                 // Explain type errors in more detail.
    "-feature",                      // Emit warning and location for usages of features that should be imported explicitly.
    "-language:existentials",        // Existential types (besides wildcard types) can be written and inferred
    "-language:experimental.macros", // Allow macro definition (besides implementation and application)
    "-language:higherKinds",         // Allow higher-kinded types
    "-language:implicitConversions", // Allow definition of implicit functions called views
    "-language:postfixOps",          // Enable postfixOps
    "-unchecked",                    // Enable additional warnings where generated code depends on assumptions.
    "-Xlint",
    "-Ywarn-numeric-widen" // Warn when numerics are widened.
  )

  val supportedScalaVersions = List(scala213)

  val sharedResolvers: Vector[MavenRepository] = Seq(
    Resolver.jcenterRepo,
    Resolver.mavenLocal,
    Resolver.sonatypeRepo("releases")
  ).toVector

  val shared: Seq[Setting[_]] = Seq(
    scalacOptions ++= sharedScalacOptions,
    crossScalaVersions := supportedScalaVersions,
    scalaVersion       := scalaV,
    ThisBuild / turbo  := true,
    resolvers          := Resolver.combineDefaultResolvers(sharedResolvers),
    compileOrder       := CompileOrder.JavaThenScala,
    organization       := "com.github.gchudnov",
    homepage           := Some(url("https://github.com/gchudnov/jaxzilla")),
    description        := "A JSON parser for Scala with SAX-style API.",
    licenses           := Seq("MIT" -> url("https://opensource.org/licenses/MIT")),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/gchudnov/jaxzilla"),
        "scm:git@github.com:gchudnov/jaxzilla.git"
      )
    ),
    developers := List(
      Developer(id = "gchudnov", name = "Grigorii Chudnov", email = "g.chudnov@gmail.com", url = url("https://github.com/gchudnov"))
    )
  )

  val noPublish: Seq[Setting[_]] = Seq(
    publishArtifact := false,
    publish         := {},
    publishLocal    := {},
    publish / skip  := true
  )

  val sonatype: Seq[Setting[_]] = Seq(
    compileOrder           := CompileOrder.JavaThenScala,
    publishMavenStyle      := true,
    Test / publishArtifact := false,
    credentials            := Seq(Credentials(Path.userHome / ".sbt" / ".credentials-sonatype")),
    usePgpKeyHex("8A64557ABEC7965C31A1DF8DE12F2C6DE96AF6D1"),
    publishTo                     := sonatypePublishToBundle.value,
    releaseCrossBuild             := true,
    releaseIgnoreUntrackedFiles   := true,
    releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      runTest,
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      releaseStepCommandAndRemaining("+publishSigned"),
      releaseStepCommandAndRemaining("sonatypeBundleRelease"),
      setNextVersion,
      commitNextVersion,
      pushChanges
    )
  )
}

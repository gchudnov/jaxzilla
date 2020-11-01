package com.github.gchudnov.jaxzilla.util

import java.io.ByteArrayInputStream

import com.github.gchudnov.jaxzilla.{ AccJsonHandler, JsonParser }

import scala.util.control.Exception.allCatch

object TestOps {

  sealed trait ParseResult
  case object Accept      extends ParseResult
  case object Reject      extends ParseResult
  case object Indifferent extends ParseResult

  object Status {
    val ExpectedResult     = "EXPECTED_RESULT"
    val ShouldHaveFailed   = "SHOULD_HAVE_FAILED"
    val ShouldHavePassed   = "SHOULD_HAVE_PASSED"
    val Crash              = "CRASH"
    val ImplementationFail = "IMPLEMENTATION_FAIL"
    val ImplementationPass = "IMPLEMENTATION_PASS"
    val Timeout            = "TIMEOUT"
  }

  final case class ReportLine(
    filename: String,
    expected: List[ParseResult],
    actual: ParseResult
  ) {
    val status: String = toStatus(expected, actual)
  }

  final case class Report(
    lines: List[ReportLine]
  )

  def toExpected(filename: String): List[ParseResult] = filename(0) match {
    case 'y' =>
      List(Accept)
    case 'n' =>
      List(Reject)
    case 'i' =>
      List(Accept, Reject)
  }

  def resultToParseResult(res: Either[Throwable, Unit]): ParseResult =
    if (res.isRight) Accept else Reject

  def toStatus(expected: List[ParseResult], actual: ParseResult): String =
    if (expected.size == 1) {
      val expectedResult = expected.head
      if (expectedResult == actual) {
        Status.ExpectedResult
      } else {
        if (expectedResult == Accept) {
          Status.ShouldHavePassed
        } else {
          Status.ShouldHaveFailed
        }
      }
    } else {
      if (expected.contains(actual)) {
        if (actual == Accept) {
          Status.ImplementationPass
        } else {
          Status.ImplementationFail
        }
      } else {
        Status.Crash
      }
    }

  def report(): Either[Throwable, Report] = allCatch.either {
    val resDir = "test_parsing/"
    val names  = FileOps.listResource("test_parsing/").toTry.get

    val lines = names.map { resName =>
      val errOrInput = FileOps.bytesFromResource(s"${resDir}${resName}")
      val input      = errOrInput.toTry.get
      val expected   = toExpected(resName)

      val is = new ByteArrayInputStream(input)

      val handler = new AccJsonHandler()
      val result  = JsonParser.parse(handler, is)
      val actual  = resultToParseResult(result)

      ReportLine(
        filename = resName,
        expected = expected,
        actual = actual
      )
    }

    Report(lines = lines)
  }
}

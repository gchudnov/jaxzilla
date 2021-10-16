package com.github.gchudnov.jaxzilla

import com.github.gchudnov.jaxzilla.util.{ ReportWriter, TestOps }
import org.scalatest.Ignore
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

@Ignore
class JsonParserReportSpec extends AnyWordSpec with Matchers {

  "JsonParserReport" when {
    "created" should {
      "build the matrix" in {
        TestOps.report().foreach(report => ReportWriter.write(report))
      }
    }
  }
}

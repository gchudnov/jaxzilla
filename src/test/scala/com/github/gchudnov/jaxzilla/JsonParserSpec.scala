package com.github.gchudnov.jaxzilla

import java.io.ByteArrayInputStream

import com.github.gchudnov.jaxzilla.util.FileOps
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class JsonParserSpec extends AnyWordSpec with Matchers {
  import com.github.gchudnov.jaxzilla.util.TestOps._

  "JsonParser" when {
    "an example object is parsed" should {
      "generate the expected events" in {
        val input    = FileOps.stringFromResource("data/example.json").toTry.get
        val expected = FileOps.stringFromResource("data/example.history").toTry.get.trim

        val is = new ByteArrayInputStream(input.getBytes())

        val handler = new AccJsonHandler()
        val result  = JsonParser.parse(handler, is)

        val actual = handler.history.trim

        result.isRight shouldBe true
        actual shouldEqual expected
      }
    }

    "parsing non-english strings" should {
      "produce the expected events" in {
        val input    = FileOps.stringFromResource("data/example-non-english.json").toTry.get
        val expected = FileOps.stringFromResource("data/example-non-english.history").toTry.get.trim

        val is = new ByteArrayInputStream(input.getBytes())

        val handler = new AccJsonHandler()
        val result  = JsonParser.parse(handler, is)

        val actual = handler.history.trim

        result.isRight shouldBe true
        actual shouldEqual expected
      }
    }

    "parsing tests" should {
      "return the expected values" in {
        val resDir = "test_parsing/"
        val names  = FileOps.listResource("test_parsing/").toTry.get

        names.foreach { resName =>
          val errOrInput = FileOps.bytesFromResource(s"${resDir}${resName}")
          val input      = errOrInput.toTry.get
          val expected   = toExpected(resName)

          val is = new ByteArrayInputStream(input)

          val handler = new AccJsonHandler()
          val result  = JsonParser.parse(handler, is)
          val actual  = resultToParseResult(result)

          if (!expected.contains(actual)) {
            println(s"file '${resName}' failed to produce the expected result")
          }

          List(actual) should contain oneElementOf (expected)
        }
      }
    }
  }
}

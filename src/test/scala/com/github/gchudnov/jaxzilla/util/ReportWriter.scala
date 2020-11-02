package com.github.gchudnov.jaxzilla.util

import java.io.PrintWriter
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

import com.github.gchudnov.jaxzilla.util.TestOps.Report

import scala.util.Using

object ReportWriter {

  def write(report: Report): Unit =
    Using.resource(new PrintWriter("res/report/report.html")) { w =>
      w.println("""<!DOCTYPE html>
                  |<HTML>
                  |<HEAD>
                  |  <TITLE>JSON Parsing Tests</TITLE>
                  |  <LINK rel="stylesheet" type="text/css" href="style.css">
                  |  <META charset="UTF-8">
                  |</HEAD>
                  |<BODY>
                  |""".stripMargin)

      val title = "JSON Parsing Tests"
      val now   = OffsetDateTime.now()

      w.println(s"<H1>${title}</H1>")
      w.println("<P>See: seriot.ch <A HREF=\"http://www.seriot.ch/parsing_json.php\">Parsing JSON is a Minefield</A> http://www.seriot.ch/parsing_json.php</P>")
      w.println(s"<PRE>${now.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)}</PRE>")

      w.println("""<A NAME="color_scheme"></A>
                  |<H4>1. Color scheme:</H4>
                  |<TABLE>
                  |  <TR><TD class="EXPECTED_RESULT">expected result</TD><TR>
                  |  <TR><TD class="SHOULD_HAVE_PASSED">parsing should have succeeded but failed</TD><TR>
                  |  <TR><TD class="SHOULD_HAVE_FAILED">parsing should have failed but succeeded</TD><TR>
                  |  <TR><TD class="IMPLEMENTATION_PASS">result undefined, parsing succeeded</TD><TR>
                  |  <TR><TD class="IMPLEMENTATION_FAIL">result undefined, parsing failed</TD><TR>
                  |  <TR><TD class="CRASH">parser crashed</TD><TR>
                  |  <TR><TD class="TIMEOUT">timeout</TD><TR>
                  |</TABLE>
                  |""".stripMargin)

      w.println("<P>")
      w.println("<H4>JAXzilla</H4>")

      w.println(
        """<TABLE>
          |  <TR>
          |    <TH></TH>
          |    <TH class="space"><DIV></DIV></TH>
          |    <TH></TH>
          |  </TR>
          |""".stripMargin
      )

      report.lines.foreach { line =>
        w.println("    <TR>")
        w.println(s"        <TD>${line.filename}</TD>")
        w.println(s"""        <TD class=\"${line.status}\"></TD>""")
        w.println("    </TR>")
      }

      w.println("</TABLE>")
      w.println("</P>")

      w.println("""</BODY>
                  |</HTML>
                  |""".stripMargin)

      w.flush()
    }

}

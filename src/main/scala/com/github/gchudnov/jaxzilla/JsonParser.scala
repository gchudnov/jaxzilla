package com.github.gchudnov.jaxzilla

import java.io.InputStream

import com.github.gchudnov.jaxzilla.internal.JsonDriver

import scala.util.control.Exception.allCatch

object JsonParser {

  /**
   * Parse the stream with the given handler
   */
  def parse(handler: JsonHandler, input: InputStream): Either[Throwable, Unit] = allCatch.either {
    val driver = new JsonDriver(input)
    driver.setHandler(handler)
    driver.any()
  }
}

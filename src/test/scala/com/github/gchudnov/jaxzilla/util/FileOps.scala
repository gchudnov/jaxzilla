package com.github.gchudnov.jaxzilla.util

import java.io.File

import scala.io.Source
import scala.util.Using
import scala.util.control.Exception.allCatch

object FileOps {

  def stringFromFile(file: File): Either[Throwable, String] =
    allCatch.either {
      Using.resource(Source.fromFile(file)) { file =>
        file.getLines().mkString("\n").trim()
      }
    }

  def stringFromResource(resourcePath: String): Either[Throwable, String] =
    allCatch.either {
      Using.resource(Source.fromResource(resourcePath)) { source =>
        source.getLines().mkString("\n").trim()
      }
    }

  def bytesFromResource(resourcePath: String): Either[Throwable, Array[Byte]] =
    allCatch.either {
      val classLoader = Thread.currentThread().getContextClassLoader
      classLoader.getResourceAsStream(resourcePath).readAllBytes()
    }

  def listResource(resourcePath: String): Either[Throwable, List[String]] =
    allCatch.either {
      Using.resource(Source.fromResource(resourcePath)) { source =>
        source.getLines().toList
      }
    }

}

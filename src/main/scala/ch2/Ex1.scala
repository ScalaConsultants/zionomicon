package ch2

import zio.{Task, ZIO}

object Ex1:
  def readFileZio(file: String): Task[String] =
    val source = scala.io.Source.fromFile(file)

    ZIO.attempt(try source.getLines.mkString("\n") finally source.close())

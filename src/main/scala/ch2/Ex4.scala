package ch2

import zio.{Task, ZIO}

object Ex4:
  def printLine(line: String): Task[Unit] = ZIO.attempt(println(line))
  val readLine: Task[String] = ZIO.attempt(scala.io.StdIn.readLine())

  for
    _ <- printLine("What is your name?")
    name <- readLine
    _ <- printLine(s"Hello, $name!")
  yield ()

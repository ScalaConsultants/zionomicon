package ch2

import zio.{Task, ZIO}

object Ex5:
  val random: Task[Int] = ZIO.attempt(scala.util.Random.nextInt(3) + 1)
  def printLine(line: String): Task[Unit] = ZIO.attempt(println(line))
  val readLine: Task[String] = ZIO.attempt(scala.io.StdIn.readLine())

  for
    int <- random
    _ <- printLine("Guess a number from 1 to 3:")
    num <- readLine
    _ <- printLine("You guessed " + (if num == int.toString then "right!" else s"wrong, the number was $int!"))
  yield ()

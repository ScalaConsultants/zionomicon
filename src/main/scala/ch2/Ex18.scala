package ch2

import zio.{Console, Random, ZIO, ZIOAppDefault}
import java.io.IOException

object Ex18 extends ZIOAppDefault:
  private val guessNumber =
    for
      _      <- Console.printLine("guess a number from 1 to 3")
      guess  <- Console.readLine
      actual <- Random.nextIntBetween(1, 4)
      _   <- Console.printLine(if guess == actual.toString then "correct" else "wrong")
    yield ()

  override def run: ZIO[Random & Console, IOException, Unit] = guessNumber

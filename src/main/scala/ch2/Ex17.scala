package ch2

import zio.{ZIO, Console, ZIOAppDefault}
import java.io.IOException

object Ex17 extends ZIOAppDefault:
  private val helloHuman = 
    for
      _    <- Console.printLine("what is your name, human?")
      name <- Console.readLine
      _    <- Console.printLine(s"hello, $name!")
    yield ()

  override def run: ZIO[Console, IOException, Unit] = helloHuman

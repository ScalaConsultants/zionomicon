package ch2

import java.io.IOException
import zio.{ZIO, Console}

object Ex19:
  def readUntil(acceptInput: String => Boolean): ZIO[Console, IOException, String] =
    lazy val readStr: ZIO[Console, IOException, String] = Console.readLine.filterOrElse(acceptInput)(readStr)

    readStr

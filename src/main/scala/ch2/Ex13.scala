package ch2

import zio.ZIO

object Ex13:
  def currentTime(): Long = java.lang.System.currentTimeMillis()

  lazy val currentTimeZIO: ZIO[Any, Nothing, Long] = ZIO.succeed(currentTime())

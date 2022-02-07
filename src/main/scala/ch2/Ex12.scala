package ch2

import zio.ZIO

object Ex12:
  def listToZIO[A](list: List[A]): ZIO[Any, None.type, A] =
    list match
      case h :: _ => ZIO.succeed(h)
      case _      => ZIO.fail(None)

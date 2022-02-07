package ch2

import zio.ZIO

object Ex11:
  def eitherToZIO[E, A](either: Either[E, A]): ZIO[Any, E, A] =
    either.fold(ZIO.fail, ZIO.succeed)

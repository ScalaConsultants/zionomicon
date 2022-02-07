package ch2

import zio.ZIO

object Ex20:
  def doWhile[R, E, A](body: ZIO[R, E, A])(condition: A => Boolean): ZIO[R, E, A] =
    lazy val loop: ZIO[R, E, A] = body.filterOrElse(condition)(loop)

    loop

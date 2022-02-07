package ch4

import zio.{IO, URIO, ZIO}

object Ex1:
  def failWithMessage(string: String): IO[Nothing, Nothing] = ZIO.fail(throw new Error(string))

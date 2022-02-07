package ch4

import zio.{Cause, ZIO}

object Ex10:
  def catchAllCause[R, E1, E2, A](zio: ZIO[R, E1, A], handler: Cause[E1] => ZIO[R, E2, A]): ZIO[R, E2, A] =
    zio.foldCauseZIO(handler, ZIO.succeed)

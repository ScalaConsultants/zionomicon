package ch4

import zio.{ZIO, Cause}

object Ex4:
  // TODO: possible errata `handler`
  def onAnyFailure[R, E, A](zio: ZIO[R, E, A], handler: Cause[E] => ZIO[R, E, A]): ZIO[R, E, A] =
    zio.foldCauseZIO(handler, ZIO.succeed)
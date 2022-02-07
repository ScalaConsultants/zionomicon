package ch4

import zio.ZIO

object Ex3:
  def logFailures[R, E, A](zio: ZIO[R, E, A]): ZIO[R, E, A] =
    zio.foldCauseZIO({ cause =>
      println(cause.prettyPrint)
      zio
    }, ZIO.succeed)

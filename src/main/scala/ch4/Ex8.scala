package ch4

import zio.ZIO

object Ex8:
  def right[R, E, A, B](zio: ZIO[R, E, Either[A, B]]): ZIO[R, Either[E, A], B] =
    zio.foldZIO({ e =>
      ZIO.fail(Left(e))
    }, _.fold({ a =>
      ZIO.fail(Right(a))
    }, ZIO.succeed))

  def unright[R, E, A, B](zio: ZIO[R, Either[E, A], B]): ZIO[R, E, Either[A, B]] =
    zio.foldZIO(_.fold(ZIO.fail, { a =>
      ZIO.succeed(Left(a))
    }), { b =>
      ZIO.succeed(Right(b))
    })

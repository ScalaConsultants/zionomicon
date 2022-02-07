package ch4

import zio.ZIO

object Ex7:
  def left[R, E, A, B](zio: ZIO[R, E, Either[A, B]]): ZIO[R, Either[E, B], A] =
    zio.foldZIO({ e =>
      ZIO.fail(Left(e))
    }, _.fold(ZIO.succeed, { b =>
      ZIO.fail(Right(b))
    }))

  def unleft[R, E, A, B](zio: ZIO[R, Either[E, B], A]): ZIO[R, E, Either[A, B]] =
    zio.foldZIO(_.fold(ZIO.fail, { b =>
      ZIO.succeed(Right(b))
    }), { a =>
      ZIO.succeed(Left(a))
    })

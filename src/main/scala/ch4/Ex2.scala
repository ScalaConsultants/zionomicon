package ch4

import zio.ZIO

object Ex2:
  def recoverFromSomeDefects[R, E, A](zio: ZIO[R, E, A])(f: Throwable => Option[A]): ZIO[R, E, A] = {
    def loop(defects: List[Throwable]): Option[A] =
      defects.headOption.flatMap { defect =>
        f(defect).fold(loop(defects.tail))(Option.apply)
      }

    zio.foldCauseZIO({ cause =>
      loop(cause.defects).fold(ZIO.failCause(cause))(ZIO.succeed)
    }, ZIO.succeed)
  }

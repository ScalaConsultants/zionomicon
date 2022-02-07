package ch2

import zio.ZIO

object Ex14:
  //noinspection AccessorLikeMethodIsUnit
  def getCacheValue(key: String, onSuccess: String => Unit, onFailure: Throwable => Unit): Unit = ???

  def getCacheValueZio(key: String): ZIO[Any, Throwable, String] =
    ZIO.async { callback =>
      getCacheValue(key, { str => 
        callback(ZIO.succeed(str))
      }, { err =>
        callback(ZIO.fail(err))
      })
    }

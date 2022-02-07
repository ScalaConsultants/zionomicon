package ch2

import zio.ZIO

object Ex15:
  trait User:
    def saveUserRecord(user: User, onSuccess: () => Unit, onFailure: Throwable => Unit): Unit = ???
    
    def saveUserRecordZio(user: User): ZIO[Any, Throwable, Unit] =
      ZIO.async { cb =>
        saveUserRecord(user, { () =>
          cb(ZIO.succeed(()))
        }, { err =>
          cb(ZIO.fail(err))
        })
      }

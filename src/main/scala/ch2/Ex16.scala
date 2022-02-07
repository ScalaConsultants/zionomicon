package ch2

import scala.concurrent.{ ExecutionContext, Future }
import zio.ZIO

object Ex16:
  trait Query
  trait Result

  def doQuery(query: Query)(implicit ec: ExecutionContext): Future[Result] = ???

  def doQueryZio(query: Query): ZIO[Any, Throwable, Result] =
    ZIO.fromFuture { implicit ec =>
      doQuery(query)
    }

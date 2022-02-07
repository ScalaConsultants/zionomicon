package ch4

import zio.ZIO

object Ex6:
  val parseNumber: ZIO[Any, NumberFormatException, Int] =
    ZIO.attempt("foo".toInt).refineOrDie {
      case nfe: NumberFormatException => nfe
    }
package ch2

import zio.test.*
import zio.test.Assertion.*
import Ex19.*

import java.io.IOException

object Ex19Spec extends DefaultRunnableSpec:
  def spec: Spec[TestConsole, TestFailure[IOException], TestSuccess] = suite("Ex19Spec")(
    test("readUntil keeps reading input until it gets the right one that it, in turn, will return") {
      val correctInput = "-999"

      for
        _  <- TestConsole.feedLines("wrong", "incorrect", "not even close", correctInput)
        res <- readUntil(_ == correctInput)
      yield assert(res)(equalTo(correctInput))
    }
  )
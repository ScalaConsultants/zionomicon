package ch2

import zio.test.*
import zio.test.Assertion.*
import Ex19.*

import java.io.IOException

object Ex19Spec extends DefaultRunnableSpec:
  def spec: Spec[TestConsole, TestFailure[IOException], TestSuccess] =
    val correctInput = "777"
    val f = readUntil(_ == correctInput)

    suite("Ex19Spec")(
      test("readUntil keeps reading input until it gets the right one that it, in turn, will return") {
        for
          _  <- TestConsole.feedLines("wrong", "incorrect", "not even close", correctInput)
          res <- f
        yield assert(res)(equalTo(correctInput))
      },
      test("do not read after it gets the right one") {
        for
          _  <- TestConsole.feedLines(correctInput, "don't")
          res <- f
        yield assert(res)(equalTo(correctInput))
      }
    )
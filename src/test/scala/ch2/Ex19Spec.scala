package ch2

import zio.test.*
import zio.test.Assertion.*
import Ex19.*

import java.io.IOException

object Ex19Spec extends DefaultRunnableSpec:
  val CorrectInput = "777"

  def spec: Spec[TestConsole, TestFailure[IOException], TestSuccess] =
    suite("Ex19Spec")(
      test("readUntil keeps reading input until it gets the right one that it, in turn, will return")(
        readUntilTest("wrong", "incorrect", "not even close", CorrectInput)
      ),
      test("do not read after it gets the right one")(
        readUntilTest(CorrectInput, "don't")
      ),
    )

  private def readUntilTest(ins: String*) =
    for
      _  <- TestConsole.feedLines(ins*)
      res <- readUntil(_ == CorrectInput)
    yield assert(res)(equalTo(CorrectInput))

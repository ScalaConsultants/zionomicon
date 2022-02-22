package ch2

import zio.test.*
import zio.test.Assertion.*
import Ex1.*

object Ex1Spec extends DefaultRunnableSpec:
  def spec: Spec[TestConsole, TestFailure[IOException], TestSuccess] = suite("Ex1Spec")(
  )

package ch2

import zio.Task

import Ex1.readFileZio
import Ex2.writeFileZio

object Ex3:
  def copyFileZio(source: String, dest: String): Task[Unit] =
    readFileZio(source).flatMap(writeFileZio(dest, _))

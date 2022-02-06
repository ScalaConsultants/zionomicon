package ch2

import zio.{Task, ZIO}
import java.io.{PrintWriter, File}

object Ex2:
  def writeFileZio(file: String, text: String): Task[Unit] =
    val pw = new PrintWriter(new File(file))

    ZIO.attempt(try pw.write(text) finally pw.close())

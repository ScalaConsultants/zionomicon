package ch2

import zio.{Chunk, Console, ExitCode, Task, URIO, ZEnv, ZIO, ZIOAppArgs}
import Ex1.readFileZio

object Ex10 extends zio.ZIOAppDefault:

  override def run: URIO[ZIOAppArgs & Console, ExitCode] =
    (for
      filenames <- getArgs
      _ <- ZIO.foreachDiscard(filenames) { filename =>
        for
          fileContent <- readFileZio(filename)
          _ <- ZIO.foreachDiscard(fileContent.linesIterator.toList) { line =>
            Console.printLine(line)
          }
        yield ()
      }
    yield ()).exitCode

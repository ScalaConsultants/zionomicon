import zio.{Console, ExitCode, Task, URIO, ZEnv, ZIO, ZIOAppArgs, Chunk}

object Ch2 extends zio.ZIOAppDefault:
  object Ex1:
    def readFileZio(file: String): Task[String] =
      val source = scala.io.Source.fromFile(file)

      ZIO.attempt(try source.getLines.mkString("\n") finally source.close())

  object Ex2:
    def writeFileZio(file: String, text: String): Task[Unit] =
      import java.io._
      val pw = new PrintWriter(new File(file))

      ZIO.attempt(try pw.write(text) finally pw.close())

  object Ex3:
    import Ex1._
    import Ex2._

    def copyFileZio(source: String, dest: String): Task[Unit] =
      readFileZio(source).flatMap(writeFileZio(dest, _))

  object Ex4:
    def printLine(line: String): Task[Unit] = ZIO.attempt(println(line))
    val readLine: Task[String] = ZIO.attempt(scala.io.StdIn.readLine())

    for
      _ <- printLine("What is your name?")
      name <- readLine
      _ <- printLine(s"Hello, $name!")
    yield ()

  object Ex5:
    val random: Task[Int] = ZIO.attempt(scala.util.Random.nextInt(3) + 1)
    def printLine(line: String): Task[Unit] = ZIO.attempt(println(line))
    val readLine: Task[String] = ZIO.attempt(scala.io.StdIn.readLine())

    for
      int <- random
      _ <- printLine("Guess a number from 1 to 3:")
      num <- readLine
      _ <- printLine("You guessed " + (if num == int.toString then "right!" else s"wrong, the number was $int!"))
    yield ()

  object Ex6:
    final case class ZIO[-R, +E, +A](run: R => Either[E, A])

    def zipWith[R, E, A, B, C](self: ZIO[R, E, A], that: ZIO[R, E, B])(f: (A, B) => C): ZIO[R, E, C] =
      ZIO { r =>
        for
          a <- self.run(r)
          b <- that.run(r)
        yield f(a, b)
      }

  object Ex7:
    import Ex6.ZIO

    def collectAll[R, E, A](in: Iterable[ZIO[R, E, A]]): ZIO[R, E, List[A]] =
      ZIO { r =>
        val (lefts, rights) = in.partitionMap(_.run(r))
        lefts.headOption.toLeft(rights.toList)
      }

  object Ex8:
    import Ex6.ZIO
    import Ex7._

    def foreach[R, E, A, B](in: Iterable[A])(f: A => ZIO[R, E, B]): ZIO[R, E, List[B]] =
      collectAll(in.map(f))

  object Ex9:
    import Ex6.ZIO

    def orElse[R, E1, E2, A](self: ZIO[R, E1, A], that: ZIO[R, E2, A]): ZIO[R, E2, A] =
      ZIO { r =>
        self.run(r).fold(_ => that.run(r), Right.apply)
      }

  // Ex 10
  import Ch2.Ex1.readFileZio

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

end Ch2

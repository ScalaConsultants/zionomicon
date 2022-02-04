import zio.{ZIO, Task}

object Ch2:
  // 1
  def readFileZio(file: String): Task[String] =
    val source = scala.io.Source.fromFile(file)

    ZIO.attempt(try source.getLines.mkString finally source.close())

  // 2
  def writeFileZio(file: String, text: String): Task[Unit] =
    import java.io._
    val pw = new PrintWriter(new File(file))

    ZIO.attempt(try pw.write(text) finally pw.close())

  // 3
  def copyFileZio(source: String, dest: String): Task[Unit] =
    readFileZio(source).flatMap(writeFileZio(dest, _))

  // 4
  def printLine(line: String): Task[Unit] = ZIO.attempt(println(line))
  val readLine: Task[String] = ZIO.attempt(scala.io.StdIn.readLine())

  for {
    _ <- printLine("What is your name?")
    name <- readLine
    _ <- printLine(s"Hello, $name!")
  } yield ()

  // 5

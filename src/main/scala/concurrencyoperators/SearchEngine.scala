package concurrencyoperators

import zio.{IO, UIO}

trait SearchEngine:
  def results(str: String): UIO[Seq[String]]
  def nSearches(userId: Int): IO[String, Int]

object Google extends SearchEngine:
  def results(str: String): UIO[Seq[String]] = UIO.succeed(Seq("1", "2"))

  // limited to 5 concurrent searches per client.
  // may be left as is
  def nSearches(userId: Int): IO[String, Int] = IO.succeed(userId * 12)

object Bing extends SearchEngine:
  def results(str: String): UIO[Seq[String]] =  UIO.succeed(Seq("1", "2", "3"))
  def nSearches(userId: Int): IO[String, Int] = ???

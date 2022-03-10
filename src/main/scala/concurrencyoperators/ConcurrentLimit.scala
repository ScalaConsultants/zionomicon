package concurrencyoperators

import zio.*

// prints out the sum of the number of searches for a list of users
object ConcurrentLimit extends ZIOAppDefault:
  def run =
    val userIds = Seq(1, 2, 3, 34, 23, 42, 55)
    lazy val searchResults = userIds.map(Google.nSearches)
    (for
      res <- ZIO.reduceAllPar(searchResults.head, searchResults.tail)(_ + _)
      _   <- Console.print(res)
    yield ()).exitCode
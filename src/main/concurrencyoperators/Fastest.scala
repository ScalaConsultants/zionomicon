package concurrencyoperators

import zio.*

// prints out search results from the fastest engine
object Fastest extends ZIOAppDefault:
  def run =
    val searchStr = "what's happening in the world today"
    (for
      res <- Bing.results(searchStr).race(Google.results(searchStr))
      _   <- Console.printLine(res)
    yield ()).exitCode
package ch2

object Ex7:
  def collectAll[R, E, A](in: Iterable[NaiveZIO[R, E, A]]): NaiveZIO[R, E, List[A]] =
    NaiveZIO { r =>
      val (lefts, rights) = in.partitionMap(_.run(r))
      lefts.headOption.toLeft(rights.toList)
    }

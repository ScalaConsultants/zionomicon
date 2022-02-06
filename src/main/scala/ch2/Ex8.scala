package ch2

import Ex7.*

object Ex8:
  def foreach[R, E, A, B](in: Iterable[A])(f: A => NaiveZIO[R, E, B]): NaiveZIO[R, E, List[B]] =
    collectAll(in.map(f))

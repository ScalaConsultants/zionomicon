package ch2

object Ex6:
  def zipWith[R, E, A, B, C](self: NaiveZIO[R, E, A], that: NaiveZIO[R, E, B])(f: (A, B) => C): NaiveZIO[R, E, C] =
    NaiveZIO { r =>
      for
        a <- self.run(r)
        b <- that.run(r)
      yield f(a, b)
    }

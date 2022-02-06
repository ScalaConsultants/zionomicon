package ch2

object Ex9:
  def orElse[R, E1, E2, A](self: NaiveZIO[R, E1, A], that: NaiveZIO[R, E2, A]): NaiveZIO[R, E2, A] =
    NaiveZIO { r =>
      self.run(r).fold(_ => that.run(r), Right.apply)
    }

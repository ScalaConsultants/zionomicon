package ch2

object Ex7:
  def collectAll[R, E, A](in: Iterable[NaiveZIO[R, E, A]]): NaiveZIO[R, E, List[A]] =
    NaiveZIO { r =>
      val (lefts, rights) = in.partitionMap(_.run(r))
      lefts.headOption.toLeft(rights.toList)
    }

  def collectAllShort[R, E, A](in: Iterable[NaiveZIO[R, E, A]]): NaiveZIO[R, E, List[A]] =
    NaiveZIO { r =>
      // using `Seq` to avoid `List.:+` (or alternatively `List.init`) thereby reducing overall time complexity by O(n)
      def loop(remainingIn: Iterable[NaiveZIO[R, E, A]], acc: Seq[A]): Either[E, Seq[A]] =
        remainingIn.headOption.fold(Right(acc))(_.run(r).flatMap { right =>
          loop(remainingIn.tail, acc :+ right)
        })

      loop(in, Seq()).map(_.toList)
    }

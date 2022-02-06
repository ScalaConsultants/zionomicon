package ch2

final case class NaiveZIO[-R, +E, +A](run: R => Either[E, A])

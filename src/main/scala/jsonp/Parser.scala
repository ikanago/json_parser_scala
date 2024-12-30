package jsonp

import cats.*
import cats.implicits.*

case class Parser[I, O](run: I => Option[(I, O)])

implicit def parserFunctor[I]: Functor[Parser[I, _]] = new Functor[Parser[I, _]] {
  override def map[A, B](fa: Parser[I, A])(f: A => B): Parser[I, B] = Parser(fa.run(_).map(_.map(f)))
}

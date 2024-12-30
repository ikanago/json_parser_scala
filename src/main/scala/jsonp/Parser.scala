package jsonp

import cats.*
import cats.implicits.*

case class Parser[I, O](run: I => Option[(I, O)])

implicit def parserFunctor[I]: Functor[[O] =>> Parser[I, O]] = new Functor[[O] =>> Parser[I, O]] {
  override def map[A, B](fa: Parser[I, A])(f: A => B): Parser[I, B] = Parser(fa.run(_).map(_.map(f)))
}

implicit def parserApplicative[I]: Applicative[[O] =>> Parser[I, O]] = new Applicative[[O] =>> Parser[I, O]] {
  override def pure[A](x: A): Parser[I, A] = Parser(s => Some((s, x)))
  override def ap[A, B](pf: Parser[I, A => B])(po: Parser[I, A]): Parser[I, B] = Parser(
    input => pf.run(input) match {
      case None => None
      case Some((rest, f)) => po.run(rest).map { case (rest, a) => (rest, f(a)) }
    }
  )
}

implicit def parserMonoidK[I]: MonoidK[[O] =>> Parser[I, O]] = new MonoidK[[O] =>> Parser[I, O]] {
  override def empty[O]: Parser[I, O] = Parser(_ => None)
  override def combineK[O](x: Parser[I, O], y: Parser[I, O]): Parser[I, O] = Parser(
    input => x.run(input) orElse y.run(input)
  )
}

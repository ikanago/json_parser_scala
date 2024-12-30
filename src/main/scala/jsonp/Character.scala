package jsonp

import cats.*
import cats.syntax.all.*

def char(c: Char): Parser[List[Char], Char] = satisfy(_ == c)

def digit: Parser[List[Char], Int] = satisfy(Character.isDigit).map(_.toString.toInt)

def string(s: String): Parser[List[Char], String] = s match {
  case s if s.nonEmpty => (char(s.head), string(s.tail)) mapN (_.toString + _)
  case _ => Applicative[[O] =>> Parser[List[Char], O]].pure("")
}

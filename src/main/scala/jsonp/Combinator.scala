package jsonp

def char1(c: Char): Parser[List[Char], Char] = Parser(
  s => if (s.head == c) Some((s.tail, c)) else None
)

def satisfy[A](pred: A => Boolean): Parser[List[A], A] = Parser(
  s => if (pred(s.head)) Some((s.tail, s.head)) else None
)

package jsonp

def satisfy[A](pred: A => Boolean): Parser[List[A], A] = Parser(
  s => if (pred(s.head)) Some((s.tail, s.head)) else None
)

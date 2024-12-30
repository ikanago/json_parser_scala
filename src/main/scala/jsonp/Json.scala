package jsonp

import cats.syntax.all.*

def jNull: Parser[List[Char], JValue] = string("null").as(JNull)

def jBool: Parser[List[Char], JValue] = string("true").as(JBool(true)) <+> string("false").as(JBool(false))

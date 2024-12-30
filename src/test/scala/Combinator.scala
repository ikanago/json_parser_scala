import jsonp.{char, satisfy}

class Combinator extends munit.FunSuite {
  test("satisfy") {
    assertEquals(satisfy(_ == 'a').run("abc".toList), Some(("bc".toList, 'a')))
  }
}

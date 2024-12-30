import jsonp.{char1, satisfy}

class Combinator extends munit.FunSuite {
  test("char1") {
    assertEquals(char1('a').run("abc".toList), Some(("bc".toList, 'a')))
  }

  test("satisfy") {
    assertEquals(satisfy(_ == 'a').run("abc".toList), Some(("bc".toList, 'a')))
  }
}

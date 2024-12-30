import jsonp.{char, digit, string}

class Character extends munit.FunSuite {
  test("char") {
    assertEquals(char('a').run("abc".toList), Some(("bc".toList, 'a')))
  }

  test("digit") {
    assertEquals(digit.run("123".toList), Some(("23".toList, 1)))
  }

  test("string") {
    assertEquals(string("abc").run("abcdef".toList), Some(("def".toList, "abc")))
  }
}

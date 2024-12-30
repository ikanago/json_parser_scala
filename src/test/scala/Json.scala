import jsonp.{JBool, JNull, jBool, jNull}

class Json extends munit.FunSuite {
  test("null") {
    assertEquals(jNull.run("null,".toList), Some((",".toList, JNull)))
  }

  test("true") {
    assertEquals(jBool.run("true,".toList), Some((",".toList, JBool(true))))
  }

  test("false") {
    assertEquals(jBool.run("false,".toList), Some((",".toList, JBool(false))))
  }
}
